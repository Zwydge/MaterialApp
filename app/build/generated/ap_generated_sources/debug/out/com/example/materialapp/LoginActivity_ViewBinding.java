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

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f080050;

  private View view7f080098;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.temail = Utils.findRequiredViewAsType(source, R.id.text_name, "field 'temail'", TextInputLayout.class);
    target.tpassword = Utils.findRequiredViewAsType(source, R.id.text_password, "field 'tpassword'", TextInputLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_add, "method 'login'");
    view7f080050 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
    view = Utils.findRequiredView(source, R.id.go_to_dahsboard, "method 'goToRegister'");
    view7f080098 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToRegister();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.temail = null;
    target.tpassword = null;

    view7f080050.setOnClickListener(null);
    view7f080050 = null;
    view7f080098.setOnClickListener(null);
    view7f080098 = null;
  }
}
