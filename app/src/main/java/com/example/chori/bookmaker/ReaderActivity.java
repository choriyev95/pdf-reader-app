package com.example.chori.bookmaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.ScrollBar;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import java.util.Locale;


public class ReaderActivity extends AppCompatActivity implements OnPageChangeListener {

    private Toolbar toolbar;
    private PDFView pdfView;
    private int totalPages;
    boolean isToolbarVisible = true;
    private int currentPage = 1;
    private AppPrefs appPrefs;

    // Starter
    public static void start(Context context) {
        final Intent starter = new Intent(context, ReaderActivity.class);

        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        init();
    }

    @Override
    public void onPageChanged(int currentPage, int totalPages) {

        setCurrentPage(currentPage);
        setTotalPages(totalPages);

        // Toolbarga sahifa raqamini yozish
        setPageNumberAsSubtitle(currentPage, totalPages);

        // Sahifa raqamini qurilma xotirasiga saqlab qo'yish
        appPrefs.savePageNumber(this.currentPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.menu.main_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbarLayout);
        setSupportActionBar(toolbar);

        // Ushbu metod activity obyektini o'ziga tegishli. Action bar set qilinganidan keyin shu
        // metoddan foydalanish maqsadga muvafiq
        setTitle("Name of the book");

        // Application context ishlatilishi shart. Joriy activity ham context sifatida ishlatishi
        // mumkin ammo bu narsa joriy activity hayot siklini tugatganidan keyin ham xotiradan
        // joy egallab turishiga olib keladi
        appPrefs = AppPrefs.getInstance(getApplicationContext());
        currentPage = appPrefs.getPageNumber();

        final ScrollBar scrollBar = (ScrollBar) findViewById(R.id.scrollBar);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.setScrollBar(scrollBar);

        // Literallardan ko'ra konstantalardan foydalanish kodni o'qilishi va kelajakda bu
        // literallarni o'zgartirilishini osonlashtiradi. Bundan tashqari har bir yangi
        // ko'rsatma alohida qatorda turgani ko'zga yaxshiroq tashlanadi
        pdfView.fromAsset(Constants.ASSET_FILE_NAME)
                .defaultPage(currentPage)
                .onPageChange(this)
                .load();

        pdfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kodni faqat farqli joyini if ni ichiga olish kerak
                final SlideTopAnimation.Direction animationDirection =
                        isToolbarVisible
                                ? SlideTopAnimation.Direction.DOWN
                                : SlideTopAnimation.Direction.UP;

                final Animation animation = new SlideTopAnimation(toolbar, animationDirection);
                toolbar.startAnimation(animation);

                isToolbarVisible = !isToolbarVisible;
            }
        });
    }

    private void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    // Chaqiriladigan metodlar chaqiruvchi metoddan pastda turishi lozim
    private void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    private void setPageNumberAsSubtitle(int currentPage, int totalPages) {
        final String pageNumberStr = String.format(
                Locale.getDefault(),
                "Page %d / %d",
                currentPage,
                totalPages
        );

        setSubtitle(pageNumberStr);
    }

    // Birgalikda bajariladigan operatsiyalarni alohida metodda qilgan ma'qul
    private void setSubtitle(String subtitle) {
        // null qaytarishi mumkin bo'lgan metodlarni doim tekshirish zarur
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(subtitle);
        }
    }
}
