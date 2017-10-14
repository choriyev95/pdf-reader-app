package com.example.chori.bookmaker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.view.animation.Animation;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.ScrollBar;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;



public class ForeachSoftLogo extends AppCompatActivity implements OnPageChangeListener {

    private Toolbar toolbar;
    private PDFView pdfView;
    private int currentPage,totalPages;
    ScrollBar scrollBar;
    boolean Visiable=true;
    private int pagenumber=1;
    private AppPrefs appPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreach_soft_logo);

        init();
    }

    public void loadComplete(int nbPages) {
        totalPages = nbPages;
    }

    @Override
    public void onPageChanged(int currentPage, int totalPages) {

        this.currentPage = currentPage;
        pagenumber=currentPage;
        getSupportActionBar().setSubtitle("Page "+currentPage+"/"+totalPages);

        // Saqlash mana shunday bo'ladi
         appPrefs.savePageNumber(pagenumber);
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

    public void init(){

        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Name of the book");
        loadComplete(totalPages);

        // Application context ishlatilishi shart
        appPrefs = AppPrefs.getInstance(getApplicationContext());
        pagenumber = appPrefs.getPageNumber();

        pdfView = (PDFView) findViewById(R.id.pdf_view);
        scrollBar = (ScrollBar) findViewById(R.id.scrollBar);
        pdfView.setScrollBar(scrollBar);
        pdfView.fromAsset("book.pdf").defaultPage(pagenumber).onPageChange(this).load();
        pdfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Visiable){
                    final Animation animation = new SlideTopAnimation(toolbar, SlideTopAnimation.Direction.UP);
                    toolbar.startAnimation(animation);
                }
                else{
                    final Animation animation = new SlideTopAnimation(toolbar, SlideTopAnimation.Direction.DOWN);
                    toolbar.startAnimation(animation);
                }
                Visiable=!Visiable;
            }
        });
    }

}
