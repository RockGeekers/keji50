define("page/account/login",["require","exports","module","components/jquery/jquery"],function(r){var e=r("components/jquery/jquery");e("#J_submit").click(function(){return e("#J_errorwrap").hide(),e.post("/account/ajax/sign_in",e("#loginForm").serialize(),function(r){0==r.code?location.href="/":e("#J_errorwrap").show().find(".error-msg").text(r.message)}),!1}),e("#J_regsub").click(function(){return e.post("/account/ajax/sign_up",e("#regForm").serialize(),function(r){0==r.code?location.href="#":e("#J_errorwrap").show().find(".error-msg").text(r.message)}),!1})});