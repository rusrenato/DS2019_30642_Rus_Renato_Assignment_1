<template>
   <div class="login-page">
                <div class="form">
                  
                  <form class="register-form" v-if="signIn">
                    <input type="text" placeholder="Name" v-model="user.name"/>
                    <input type="email" placeholder="Email" v-model="user.email">
                    <input type="password" placeholder="Password" v-model="user.password"/>
                    <input type="text" placeholder="Address" v-model="user.address"/>

                    <div id="radios">
                        <label>Gender: </label>
                        <label>Male</label>
                        <input type="radio" value="Male" v-model="user.gender" />
                        <label>Female</label>
                        <input type="radio" value="Female" v-model="user.gender" />
                    </div>

                    <button @click.prevent="registerDoctor">create</button>
                    <p class="message">Already registered? </p> <p @click="change">Sign In<p/>
                  </form>

                  <form class="login-form" v-if="!signIn">
                    <input type="text" id="email" v-model="loginEmail" placeholder="Email"/>
                    <input type="password" v-model="loginPassword" placeholder="Password"/>
                    <button @click.prevent="login">login</button>

                    <p class="message">Not registered? </p> <p @click="change">Create an account</p>
                  </form>
                </div>
              
        </div>
</template>

<script>

export default {
  data () {
    return {
        user : {
          name: "",
          email: "",
          password: "",
          address: "",
          gender: "",
       },
        signIn: false, 
        token : "", 
        loginPassword : "",
        loginEmail : "", 
        authenticated : false,
        loggedId : "",
        loggedUser : {},
     
    }
  },
  methods: {
      change: function(){
        this.signIn = !this.signIn;
        console.log(this.signIn)
      },
       registerDoctor: function(){
       this.$http.post('http://localhost:8081/user/register',{
              "name" :  this.user.name,
	            "gender" : this.user.gender,
	            "address" :this.user.address,
              "email" : this.user.email,
              "password" : this.user.password,
              "rol" : 0
       }).then (function(data){
         this.signIn = false;
         console.log(data);
       })
    },
    getUser: function(){
      const auth = {
        headers: {Authorization:'Bearer ' + this.token} 
       }
       this.$http.get('http://localhost:8081/user/logged', auth).then(function(data){
       this.loggedUser = data.body; 
       console.log(this.loggedUser);
       this.loggedId = data.body.id;
       localStorage.setItem('userRole', this.loggedUser.rol);
       localStorage.setItem('loggedId', this.loggedId);
       var path = this.loggedUser.rol + '';
       this.redirect(path);
      })
    },
    redirect: function(path) {
        this.$router.push('/' + path.toLowerCase());
    },

    login: function(){
        const auth = {
                  headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',          
                    'Authorization':'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0'
                   } 
        }
        let params = new URLSearchParams();
        params.append('grant_type', 'password');
        params.append('username', this.loginEmail);
        params.append('password', this.loginPassword);
      
        this.$http.post('http://localhost:8081/oauth/token', params, auth).then(function(data){
        this.token = data.body.access_token;

        this.authenticated = true;
        console.log("Succes ! ");
        console.log(this.token);
        this.getUser();
      })

    },
   
  },
  watch: {
    token(newToken){
      localStorage.token = newToken;
    },
    loggedId(newloggedId){
      localStorage.loggedId = newloggedId;
    }
  }
}
</script>




<style scoped>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
.form {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: auto;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background: #4CAF50;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}
.form button:hover,.form button:active,.form button:focus {
  background: #43A047;
}
.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}

.container {
  position: relative;
  z-index: 1;
  max-width: 300px;
  margin: 0 auto;
}
.container:before, .container:after {
  content: "";
  display: block;
  clear: both;
}
.container .info {
  margin: 50px auto;
  text-align: center;
}
.container .info h1 {
  margin: 0 0 15px;
  padding: 0;
  font-size: 36px;
  font-weight: 300;
  color: #1a1a1a;
}
.container .info span {
  color: #4d4d4d;
  font-size: 12px;
}
.container .info span a {
  color: #000000;
  text-decoration: none;
}
.container .info span .fa {
  color: #EF3B3A;
}
template {
  background: #092525; /* fallback for old browsers */
}

#radios{
    display: inline-block;
    margin-right: 10px;
    
}
</style>
