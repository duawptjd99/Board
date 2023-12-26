class login {

    userId;
    userPw;
    signUpButton;
    loginButton;
    loginForm;

    constructor() {
        this.userId = document.getElementById("user-id");
        this.userPw = document.getElementById("user-pw");
        this.signUpButton = document.getElementById("signup-button");
        this.loginForm = document.getElementById("login-form");
        this.loginButton = document.getElementById("login-button")

        this.signUpButton.addEventListener("click", this.signUp);
        this.loginButton.addEventListener("click", this.submitLoginForm);
    }

    submitLoginForm = (event) => {
        event.preventDefault();
        if (this.checkLogin()) {
            this.loginForm.action = "/login";
            this.loginForm.method = "POST";
            this.loginForm.submit();
        }
    }

    signUp = (event) => {
        event.preventDefault();
        window.location.href = "/signup";
    }

    checkLogin = () => {
        if (this.userId.value === "") {
            alert("아이디가 작성되지 않았습니다.");

            return false;
        }

        if (this.userPw.value === "") {
            alert("비밀번호가 작성되지 않았습니다.");

            return false;
        }

        return true;
    }
}
