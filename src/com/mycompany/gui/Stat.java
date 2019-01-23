/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author Insaf-Nefzi
 */
public class Stat {
    Form f;
    double[] values ;
    
    /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
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
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    series.add("Sucre",values[0]);
        series.add("Sale",values[1]);
         //   series.add("En maintenance",values[2]);

//    for (double value : values) {
//        series.add("Project " + ++k, value);
//    int k = 0;
//    for (double value : values) {
//        series.add("Project " + ++k, value);
//    }

    return series;
}

public Form createPieChartForm() {
    // Generate the values
    
    ServiceProduit ser=new ServiceProduit();
     double d1 = Float.parseFloat(ser.etat("Sucre"));
     double d2= Float.parseFloat(ser.etat("Sale"));
     //double d3 = Float.parseFloat(ser.etat("En maintenance"));
     System.out.println(d1);
       System.out.println(d2);
    // System.out.println(d3);

   // double[] 
            values = new double[]{d1, d2};
   // double[] values = new double[]{12, 14, 11, 10, 19};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE,  ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(200);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    
  f = new Form("Budget", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    return f;
}

    public Stat() {
        ServiceProduit ser=new ServiceProduit();
     double d1 = Float.parseFloat(ser.etat("Sucre"));
     double d2= Float.parseFloat(ser.etat("Sale"));
     //double d3 = Float.parseFloat(ser.etat("En maintenance"));
     System.out.println(d1);
       System.out.println(d2);
    // System.out.println(d3);

   // double[] 
            values = new double[]{d1, d2};
   // double[] values = new double[]{12, 14, 11, 10, 19};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE,  ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(75);
    renderer.setDisplayValues(true);
    renderer.setLabelsTextSize(70);
    renderer.setLabelsColor(ColorUtil.CYAN);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    
  f = new Form("Budget", new BorderLayout());
  //f.add(new Label("Statistique selon categorie"));
    f.add(BorderLayout.CENTER, c);
    
         f.show();
       // f=new Form();
               
           f.getToolbar().addCommandToRightBar("back", null, (ev)->{
               AffichageProduit h=new AffichageProduit();
     h.getF().show();});
                     
        
        Toolbar tb2=f.getToolbar();
         tb2.addMaterialCommandToLeftSideMenu("About", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           // h3.show();
        }
        });
           tb2.addMaterialCommandToLeftSideMenu("Ajouter Produit", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           AjouterProduit h = new AjouterProduit();
   h.getF().show();
        }
        });
             tb2.addMaterialCommandToLeftSideMenu("Afficher Produit", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            AffichageProduit a=new AffichageProduit();
        a.getF().show();
        }
        });
               tb2.addMaterialCommandToLeftSideMenu("Afficher Commande", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
           AfficherCommande a=new AfficherCommande();
        a.getF().show();
        }
        });
        
                       tb2.addMaterialCommandToLeftSideMenu("Afficher Favoris", FontImage.MATERIAL_SETTINGS, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
              Fav a=new Fav();
        a.getH1().show();
        }
        });
    }

    public Form getF() {
        return f;
    }
    

    
}
