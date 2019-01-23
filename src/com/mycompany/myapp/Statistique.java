/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.EvenementService;
import com.mycompany.entities.Evenements;
import java.util.ArrayList;

/**
 *
 * @author Ruskov
 */
public final class Statistique extends BaseForm{
    public Statistique(Resources res,Evenements ev)
    {
         
      Toolbar tb = new Toolbar(true);
       Container cnt1=new Container() ;
//        setToolbar(tb);
//        tb.setUIID("Container");
//        getTitleArea().setUIID("Container");
//        Form previous = Display.getInstance().getCurrent();
//        tb.setBackCommand("back", e -> previous.showBack());
//        setUIID("Activate");
       
        createPieChartForm(ev);

    }
    
    
    
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(30);
    renderer.setLegendTextSize(30);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) 
{
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    series.add("Total des participations",values[0]);
    series.add("Places restantes",values[1]);
    return series;
}

public void createPieChartForm(Evenements ev) {
    // Generate the values
//    SEvenement se=new SEvenement();
int restant=ev.getCapacite()-ev.getInteresses();
    double[] values = new double[]{ev.getInteresses(),restant};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.MAGENTA};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setChartTitleTextSize(30);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setLabelsColor(ColorUtil.BLACK);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.GREEN);
    r.setGradientStop(0, ColorUtil.BLUE);
    
    r.setHighlighted(true);
    r.setColor(ColorUtil.BLACK);
    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Statistiques de participation ", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
     Label l1=new Label("          Statistiques sur les participations à ");
     Label l2=new Label("                        "+ev.getNomE());
   Container cnt=new Container();
   cnt.add(l1);
    cnt.add(l2);
    cnt.add(c);
  add(cnt);

}

public static Button createBackBtn(){
         Button b=new Button("<<Retour arrière");
      //   b.getUnselectedStyle().setFgColor(5542241);
         b.addActionListener((ActionListener) (ActionEvent evt) -> {
          ListEvenements list=new ListEvenements();
 
        list.show();    // 
         });
         return b;
     }
}
