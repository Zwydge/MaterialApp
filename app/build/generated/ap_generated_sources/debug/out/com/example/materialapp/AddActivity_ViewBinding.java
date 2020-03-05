// Generated code from Butter Knife. Do not modify!
package com.example.materialapp;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddActivity_ViewBinding implements Unbinder {
  private AddActivity target;

  private View view7f08009b;

  private View view7f080040;

  @UiThread
  public AddActivity_ViewBinding(AddActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddActivity_ViewBinding(final AddActivity target, View source) {
    this.target = target;

    View view;
    target.tname = Utils.findRequiredViewAsType(source, R.id.text_name, "field 'tname'", TextInputLayout.class);
    view = Utils.findRequiredView(source, R.id.go_to_dahsboard, "method 'goToDashbard'");
    view7f08009b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToDashbard();
      }
    });
    view = Utils.findRequiredView(source, R.id.add_mat, "method 'add'");
    view7f080040 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.add();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AddActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tname = null;

    view7f08009b.setOnClickListener(null);
    view7f08009b = null;
    view7f080040.setOnClickListener(null);
    view7f080040 = null;
  }
}
