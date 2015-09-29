// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the MIT License https://raw.github.com/mit-cml/app-inventor/master/mitlicense.txt

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.appinventor.client.output.OdeLog;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * micomponente component.
 *
 * @author email@example.com (First Last)
 */
public final class Mockmicomponente extends MockVisibleComponent{

/**
 * Component type name.
 */
public static final String TYPE = "micomponente";
private static final int DEFAULT_WIDTH = LENGTH_FILL_PARENT;
private static final String PROPERTY_NAME_PICTURE = "Picture";
  private static final String PROPERTY_SCALE_PICTURE_TO_FIT = "ScalePictureToFit";

/**
 * Creates a new Mockmicomponente component.
 *
 * @param editor  editor of source file the component belongs to
 */

//Widget for showing the mock component
 private InlineLabel labelWidget;
 private final Image image;
 private String picturePropValue;
 private boolean fitToScale;
 
 /**
  * Creates a new Mockmicomponente component.
  *
  * @param editor editor of source file the component belongs to
  */
 public Mockmicomponente(SimpleEditor editor) {
   super(editor, TYPE, images.micomponente());

   // Initialize mock label UI
   labelWidget = new InlineLabel();
   labelWidget.setText("Label prueba");
   labelWidget.setStylePrimaryName("ode-SimpleMockComponent");
//   initComponent(labelWidget);
   
   
    image = new Image();
       image.addErrorHandler(new ErrorHandler() {
         @Override
         public void onError(ErrorEvent event) {
           if (picturePropValue != null && !picturePropValue.isEmpty()) {
             OdeLog.elog("Error occurred while loading image " + picturePropValue);
           }
           refreshForm();
         }
       });
       image.addLoadHandler(new LoadHandler() {
         @Override
         public void onLoad(LoadEvent event) {
           refreshForm();
         }
       });
       VerticalPanel panel = new VerticalPanel();
       panel.setStylePrimaryName("ode-SimpleMockComponent");
       panel.addStyleName("imageComponentCenterPanel");
       panel.add(labelWidget);
       panel.add(image);
       initComponent(panel);
//    refreshForm();
 }


 @Override
 protected boolean isPropertyVisible(String propertyName) {
   //We don't want to allow user to change the component height. S/he can only change the
   //component width
   if (propertyName.equals(PROPERTY_NAME_HEIGHT)) {
     return false;
   }
   return super.isPropertyVisible(propertyName);
 }

// @Override
// public int getPreferredWidth() {
//   // The superclass uses getOffsetWidth, which won't work for us.
//   return DEFAULT_WIDTH;
// }°
 
 /*
   * Sets the image's url to a new value.
   */
  private void setPictureProperty(String text) {
    picturePropValue = text;
    String url = convertImagePropertyValueToUrl(text);
    if (url == null) {
      // text was not recognized as an asset. Just display the icon for this type of component.
      Image iconImage = getIconImage();
      image.setUrlAndVisibleRect(iconImage.getUrl(),
          iconImage.getOriginLeft(), iconImage.getOriginTop(),
          iconImage.getWidth(), iconImage.getHeight());
    } else {
      image.setUrl(url);
    }
  }

  @Override
  public int getPreferredWidth() {
    // The superclass uses getOffsetWidth, which won't work for us.
    // Hide away the current 100% size so we can get at the actual size, otherwise automatic size doesn't work
    String[] style = MockComponentsUtil.clearSizeStyle(image);
    int width = image.getWidth() + labelWidget.getOffsetWidth();
    MockComponentsUtil.restoreSizeStyle(image, style);
    return width;
  }

  @Override
  public int getPreferredHeight() {
    // The superclass uses getOffsetHeight, which won't work for us.
    // Hide away the current 100% size so we can get at the actual size, otherwise automatic size doesn't work
    String[] style = MockComponentsUtil.clearSizeStyle(image);
    int height = image.getHeight() + labelWidget.getOffsetHeight();
    MockComponentsUtil.restoreSizeStyle(image, style);
    return height;
  }

  /**
   * property to make the picture scale to fit its parents width and height
   * @param newValue true will scale the picture
   */
  private void setScalingProperty(String newValue) {
    if (newValue.equals("True")){
      fitToScale = true;
      image.setSize("100%", "100%");
    }

    else {
      fitToScale = false;
      image.setSize(getPreferredWidth() + "px", getPreferredHeight() + "px");
    }
  }

 // PropertyChangeListener implementation
 @Override
 public void onPropertyChange(String propertyName, String newValue) {
   super.onPropertyChange(propertyName, newValue);
   
   // Apply changed properties to the mock component
    if (propertyName.equals(PROPERTY_NAME_PICTURE)) {
      setPictureProperty(newValue);
      refreshForm();
    }
    else if (propertyName.equals(PROPERTY_SCALE_PICTURE_TO_FIT)) {
      setScalingProperty(newValue);
      refreshForm();

    }
    else if (propertyName.equals(PROPERTY_NAME_WIDTH)) {
      image.setWidth(newValue + "px");
      refreshForm();

    }
    else if (propertyName.equals(PROPERTY_NAME_HEIGHT)) {
      image.setHeight(newValue + "px");
      refreshForm();
    }
 }
}