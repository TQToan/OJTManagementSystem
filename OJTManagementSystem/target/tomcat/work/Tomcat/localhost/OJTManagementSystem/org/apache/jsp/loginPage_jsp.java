/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-06-02 03:39:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loginPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue.release();
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <title>Login</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/font/fontawesome-free-6.1.1-web/css/all.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/font/bootstrap-5.2.0-beta1/dist/css/bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/css/base.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./assets/css/login.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header class=\"header \">\n");
      out.write("\n");
      out.write("            <div class=\"navbar\">\n");
      out.write("                <a href=\"#\" class=\"header__logo \">\n");
      out.write("                    <img src=\"./assets/img/logo.png\" alt=\"\" class=\"logo\">\n");
      out.write("                </a>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("\n");
      out.write("        <div class=\"main\">\n");
      out.write("\n");
      out.write("            <h1 class=\"heading\">Welcome to FPT_OJT</h1>\n");
      out.write("            <div class=\"row main-body\">\n");
      out.write("                <div class=\"container-left offset-1 col-4\">\n");
      out.write("                    <h2 class=\"container__heading\">*Company</h2>\n");
      out.write("                    <div class=\"heading-btn\">\n");
      out.write("                        <a href=\"#\" class=\"heading-btn--btn\">Login</a>\n");
      out.write("\n");
      out.write("                        <a href=\"RegisterPage1\" class=\"heading-btn--btn\">Sign Up</a>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"form\">\n");
      out.write("                        <form action=\"loginAccountsController\" method=\"post\">\n");
      out.write("                            <h5 class=\"text-danger\">\n");
      out.write("                                ");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                            </h5>\n");
      out.write("                            <input type=\"text\" class=\"form-input\" name=\"txtEmail\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.txtEmail}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" placeholder=\"Enter your email\"> <br>\n");
      out.write("                            <h5 class=\"text-danger\">\n");
      out.write("                                ");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                            </h5>\n");
      out.write("                            <input type=\"password\" class=\"form-input\" name=\"txtPassword\" value=\"\" placeholder=\"Enter your password\">\n");
      out.write("                            <h5 class=\"text-danger\">\n");
      out.write("                                ");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                            </h5>\n");
      out.write("                            <!-- <div class=\"checkbox-btn\">\n");
      out.write("                                <input type=\"checkbox\" id=\"auto-fill\">\n");
      out.write("                                <label for=\"auto-fill\">\n");
      out.write("                                    Auto Login\n");
      out.write("                                </label>\n");
      out.write("                            </div> -->\n");
      out.write("\n");
      out.write("                            <input type=\"submit\" value=\"Login\" class=\"login-btn\">\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"container-right offset-1 col-5\">\n");
      out.write("                    <h2 class=\"container__heading\">*FPT University</h2>\n");
      out.write("                    <p class=\"container__content\">\n");
      out.write("                        Job information page for FPT university students: <br>\n");
      out.write("                        - More than 60 companies <br>\n");
      out.write("                        - Many Professions in all fields <br>\n");
      out.write("                        - Moderated by the school\n");
      out.write("                    </p>\n");
      out.write("\n");
      out.write("                    ");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    <h5 class=\"text-danger text-center\">\n");
      out.write("                        ");
      if (_jspx_meth_c_005fset_005f1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                        ");
      if (_jspx_meth_c_005fif_005f3(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                    </h5>\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginGoogle}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"google-btn row\">\n");
      out.write("\n");
      out.write("                        <i class=\"fab fa-google google-logo col-2 \"></i>\n");
      out.write("                        <span class=\"google-content col-10 \">Login email @fpt.edu.vn</span>\n");
      out.write("                    </a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <footer class=\"footer\">\n");
      out.write("            <div class=\"footer__content\">\n");
      out.write("                @copyright 2022\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /loginPage.jsp(46,32) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("error");
    // /loginPage.jsp(46,32) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/loginPage.jsp(46,32) '${requestScope.ERROR}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${requestScope.ERROR}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /loginPage.jsp(47,32) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty error}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                    <font color=\"red\">\n");
        out.write("                                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error.accountError}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("\n");
        out.write("                                    </font>\n");
        out.write("                                ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /loginPage.jsp(55,32) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty error}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                    <font color=\"red\">\n");
        out.write("                                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error.userEmailEmpty}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("\n");
        out.write("                                    </font>\n");
        out.write("                                ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent(null);
    // /loginPage.jsp(63,32) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty error}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                    <font color=\"red\">\n");
        out.write("                                    ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error.userPasswordEmpty}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("\n");
        out.write("                                    </font>\n");
        out.write("                                ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /loginPage.jsp(90,20) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setVar("loginGoogle");
    // /loginPage.jsp(90,20) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("https://accounts.google.com/o/oauth2/auth");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_eval_c_005furl_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_c_005furl_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_c_005furl_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_c_005furl_005f0.doInitBody();
      }
      do {
        out.write("\n");
        out.write("                        ");
        if (_jspx_meth_c_005fparam_005f0(_jspx_th_c_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        ");
        if (_jspx_meth_c_005fparam_005f1(_jspx_th_c_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        ");
        if (_jspx_meth_c_005fparam_005f2(_jspx_th_c_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        ");
        if (_jspx_meth_c_005fparam_005f3(_jspx_th_c_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                        ");
        if (_jspx_meth_c_005fparam_005f4(_jspx_th_c_005furl_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                    ");
        int evalDoAfterBody = _jspx_th_c_005furl_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_c_005furl_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvar_005fvalue.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.rt.core.ParamTag _jspx_th_c_005fparam_005f0 = (org.apache.taglibs.standard.tag.rt.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ParamTag.class);
    _jspx_th_c_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f0);
    // /loginPage.jsp(91,24) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f0.setName("scope");
    // /loginPage.jsp(91,24) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f0.setValue("email profile openid");
    int _jspx_eval_c_005fparam_005f0 = _jspx_th_c_005fparam_005f0.doStartTag();
    if (_jspx_th_c_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.rt.core.ParamTag _jspx_th_c_005fparam_005f1 = (org.apache.taglibs.standard.tag.rt.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ParamTag.class);
    _jspx_th_c_005fparam_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f0);
    // /loginPage.jsp(92,24) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f1.setName("redirect_uri");
    // /loginPage.jsp(92,24) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f1.setValue("http://localhost:8080/OJTManagementSystem/LoginGoogleServlet");
    int _jspx_eval_c_005fparam_005f1 = _jspx_th_c_005fparam_005f1.doStartTag();
    if (_jspx_th_c_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.rt.core.ParamTag _jspx_th_c_005fparam_005f2 = (org.apache.taglibs.standard.tag.rt.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ParamTag.class);
    _jspx_th_c_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f0);
    // /loginPage.jsp(93,24) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f2.setName("response_type");
    // /loginPage.jsp(93,24) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f2.setValue("code");
    int _jspx_eval_c_005fparam_005f2 = _jspx_th_c_005fparam_005f2.doStartTag();
    if (_jspx_th_c_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.rt.core.ParamTag _jspx_th_c_005fparam_005f3 = (org.apache.taglibs.standard.tag.rt.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ParamTag.class);
    _jspx_th_c_005fparam_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f0);
    // /loginPage.jsp(94,24) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f3.setName("client_id");
    // /loginPage.jsp(94,24) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f3.setValue("206603271676-8up4i9nf258hmlcol6khi7hcfkf7ibcn.apps.googleusercontent.com");
    int _jspx_eval_c_005fparam_005f3 = _jspx_th_c_005fparam_005f3.doStartTag();
    if (_jspx_th_c_005fparam_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fparam_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005furl_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:param
    org.apache.taglibs.standard.tag.rt.core.ParamTag _jspx_th_c_005fparam_005f4 = (org.apache.taglibs.standard.tag.rt.core.ParamTag) _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ParamTag.class);
    _jspx_th_c_005fparam_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fparam_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005furl_005f0);
    // /loginPage.jsp(96,24) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f4.setName("approval_prompt");
    // /loginPage.jsp(96,24) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fparam_005f4.setValue("force");
    int _jspx_eval_c_005fparam_005f4 = _jspx_th_c_005fparam_005f4.doStartTag();
    if (_jspx_th_c_005fparam_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fparam_0026_005fvalue_005fname_005fnobody.reuse(_jspx_th_c_005fparam_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f1.setParent(null);
    // /loginPage.jsp(99,24) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("error");
    // /loginPage.jsp(99,24) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue(new org.apache.jasper.el.JspValueExpression("/loginPage.jsp(99,24) '${requestScope.ERROR}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${requestScope.ERROR}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent(null);
    // /loginPage.jsp(100,24) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty error}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                            <font color=\"red\">\n");
        out.write("                            ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${error.userEmailNotAllow}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("\n");
        out.write("                            </font>\n");
        out.write("                        ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }
}
