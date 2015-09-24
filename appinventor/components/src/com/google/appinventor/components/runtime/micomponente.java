// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the MIT License https://raw.github.com/mit-cml/app-inventor/master/mitlicense.txt

package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.TextViewUtil;


import android.view.View;
import android.widget.TextView;


@DesignerComponent(version = YaVersion.MICOMPONENTE_COMPONENT_VERSION,
    description = "Smart Town component.",
    category = ComponentCategory.USERINTERFACE,
    nonVisible = false,
    iconName = "images/micomponente.png")
@SimpleObject
public final class micomponente extends AndroidViewComponent {

  // change to reflect the view of the component
  private final TextView view;
  
  int propiedad;

   @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public int Propiedad() {
        return propiedad;
    }
    
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_INTEGER, defaultValue = "0")
    public void Propiedad(int propiedad) {
        this.propiedad = propiedad;
    }

  
  
  
  /**
   * Creates a new component.
   *
   * @param container  container, component will be placed in
   */
  public micomponente(ComponentContainer container) {
    super(container);
    view = new TextView(container.$context());

    // Adds the component to its designated container
    container.$add(this);

    // Default property values
    TextViewUtil.setText(view, "your new micomponente");
  }

  @Override
  public View getView() {
    return view;
  }

}
