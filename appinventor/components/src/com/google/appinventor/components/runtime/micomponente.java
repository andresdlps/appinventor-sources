// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the MIT License https://raw.github.com/mit-cml/app-inventor/master/mitlicense.txt

package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;




@DesignerComponent(version = YaVersion.MICOMPONENTE_COMPONENT_VERSION,
    description = "Smart Town component.",
    category = ComponentCategory.USERINTERFACE,
    nonVisible = false,
    iconName = "images/micomponente.png")
@SimpleObject
public final class micomponente extends AndroidViewComponent {

  // change to reflect the view of the component
  private final TextView nombreProducto;
  
  private final ImageView view;
  
  private String picturePath = ""; 

   /*@SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public Image ImagenProducto() {
        return imagenProducto;
    }
    
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_INTEGER, defaultValue = "0")
    public void ImagenProducto(Image imagenProducto) {
        this.imagenProducto = imagenProducto;
    }*/  
  
  
  /**
   * Creates a new component.
   *
   * @param container  container, component will be placed in
   */
  public micomponente(ComponentContainer container) {
    super(container);
    nombreProducto = new TextView(container.$context());
    view = new ImageView(container.$context()) {
      @Override
      public boolean verifyDrawable(Drawable dr) {
        super.verifyDrawable(dr);
        // TODO(user): multi-image animation
        return true;
      }
    };
      /*imagenProducto = new ImageView(container.$context()) {
      @Override
        public boolean verifyDrawable(Drawable dr) {
          super.verifyDrawable(dr);
          // TODO(user): multi-image animation
          return true;
        }
      };*/
    // Adds the component to its designated container
    container.$add(this);
    view.setFocusable(true);
    // Default property values
    /*TextViewUtil.setText(nombreProducto, "NOMBRE DEL PRODUCTO");
    TextViewUtil.setFontSize(nombreProducto, (float) 20.0);
    TextViewUtil.setFontTypeface(nombreProducto, TYPEFACE_DEFAULT, true, false);*/
    
  }

  @Override
  public View getView() {
    return view;
  }

  /**
   * Returns the path of the image's picture.
   *
   * @return  the path of the image's picture
   */
  @SimpleProperty(
      category = PropertyCategory.APPEARANCE)
  public String Picture() {
    return picturePath;
  }

  /**
   * Specifies the path of the image's picture.
   *
   * <p/>See {@link MediaUtil#determineMediaSource} for information about what
   * a path can be.
   *
   * @param path  the path of the image's picture
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET,
      defaultValue = "")
  @SimpleProperty
  public void Picture(String path) {
    picturePath = (path == null) ? "" : path;

    Drawable drawable;
    try {
      drawable = MediaUtil.getBitmapDrawable(container.$form(), picturePath);
    } catch (IOException ioe) {
      Log.e("Image", "Unable to load " + picturePath);
      drawable = null;
    }

    ViewUtil.setImage(view, drawable);
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
      defaultValue = "False")
  @SimpleProperty
  public void ScalePictureToFit(boolean scale) {
    if (scale)
      view.setScaleType(ImageView.ScaleType.FIT_XY);
  }

  /**
   * Animation property setter method.
   *
   * @see AnimationUtil
   *
   * @param animation  animation kind
   */
  @SimpleProperty(description = "This is a limited form of animation that can attach " +
      "a small number of motion types to images.  The allowable motions are " +
      "ScrollRightSlow, ScrollRight, ScrollRightFast, ScrollLeftSlow, ScrollLeft, " +
      "ScrollLeftFast, and Stop",
      category = PropertyCategory.APPEARANCE)
  // TODO(user): This should be changed from a property to an "animate" method, and have the choices
  // placed in a dropdown.  Aternatively the whole thing should be removed and we should do
  // something that is more consistent with sprites.
  public void Animation(String animation) {
    AnimationUtil.ApplyAnimation(view, animation);
  }
}
