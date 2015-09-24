// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the MIT License https://raw.github.com/mit-cml/app-inventor/master/mitlicense.txt

package com.google.appinventor.client.editor.simple.components;

import static com.google.appinventor.client.Ode.MESSAGES;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.Messages.DefaultMessage;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

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
private static final int DEFAULT_WIDTH = 100;


/**
 * Creates a new Mockmicomponente component.
 *
 * @param editor  editor of source file the component belongs to
 */

//Widget for showing the mock component
 private InlineLabel labelWidget;



 /**
  * Creates a new Mockmicomponente component.
  *
  * @param editor editor of source file the component belongs to
  */
 public Mockmicomponente(SimpleEditor editor) {
   super(editor, TYPE, images.micomponente());

   // Initialize mock label UI
   labelWidget = new InlineLabel();
   labelWidget.setStylePrimaryName("ode-SimpleMockComponent");
   labelWidget.setText("your new micomponente");
   initComponent(labelWidget);
   refreshForm();
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

 @Override
 public int getPreferredWidth() {
   // The superclass uses getOffsetWidth, which won't work for us.
   return DEFAULT_WIDTH;
 }

 // PropertyChangeListener implementation
 @Override
 public void onPropertyChange(String propertyName, String newValue) {
   super.onPropertyChange(propertyName, newValue);

 }
}