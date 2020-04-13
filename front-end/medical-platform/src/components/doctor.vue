<template>
  <div>
    <div class="sidebar">
      <p :class="className1" @click="changeMenu('create')">Create</p>
      <p :class="className2" @click="changeMenu('view')">View</p>
      <p :class="className3" @click="changeMenu('update')">Update</p>
      <p @click="logout">Logout</p>
    </div>

  
    <create-menu v-if="show == 'create'" user={} ></create-menu>
    <view-menu v-if="show == 'view'"></view-menu>
    <update-menu v-if="show == 'update'"></update-menu>
   
  </div>
</template>

<script>
import viewMenu from "./view-menu"
import createMenu from "./create-menu"
import updateMenu from "./update-menu"

export default {
  components: {
    "view-menu" : viewMenu,
    "create-menu" : createMenu,
    "update-menu" : updateMenu,
  },
  data() {
    return {
      loggedId: "",
      token: "",
      show: "create",
      className1: "active",
      selectedRole: "",
      className2: "",
      className3: "",
      userRole: "",
    };
  },
  methods: {
    changeMenu: function(name) {
      this.show = name;
      switch (name) {
        case "create":
          console.log("Aici" + this.loggedUser)
          this.className1 = "active";
          this.className2 = "";
          this.className3 = "";
          break;
        case "view":
          this.className1 = "";
          this.className2 = "active";
          this.className3 = "";
          break;
        case "update":
          this.className1 = "";
          this.className2 = "";
          this.className3 = "active";
          break;
      }
    },
   
    logout: function() {
        localStorage.removeItem('token');
        localStorage.removeItem('loggedId');
        localStorage.removeItem('userRole');
        this.$router.push('/login');
    }
  },
  mounted() {
    if(localStorage.token) 
      this.token = localStorage.token;
    if(localStorage.loggedId) 
      this.loggedId = localStorage.loggedId;
    if(localStorage.userRole)
      this.userRole = localStorage.userRole;
  },
  watch: {
    token(newToken){
      localStorage.token = newToken;
    },
    loggedId(newLoggedId){
      localStorage.loggedId = newLoggedId;
    },
    userRole(newUserRole){
      localStorage.userRole = newUserRole;
    }
  }
};
</script>

<style scoped>
/* The side navigation menu */
.sidebar {
  margin: 0;
  padding: 0;
  width: 200px;
  background-color: #f1f1f1;
  position: fixed;
  height: 100%;
  overflow: auto;
}

/* Sidebar links */
.sidebar p {
  display: block;
  color: black;
  padding: 16px;
  text-decoration: none;
}

/* Active/current link */
.sidebar p.active {
  background-color: #4caf50;
  color: white;
}

/* Links on mouse-over */
.sidebar p:hover:not(.active) {
  background-color: #555;
  color: white;
}

/* Page content. The value of the margin-left property should match the value of the sidebar's width property */
div.content {
  margin-left: 200px;
  padding: 1px 16px;
  height: 1000px;
}

/* On screens that are less than 700px wide, make the sidebar into a topbar */
@media screen and (max-width: 700px) {
  .sidebar {
    width: 100%;
    height: auto;
    position: relative;
  }
  .sidebar p {
    float: left;
  }
  div.content {
    margin-left: 0;
  }
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
  .sidebar p {
    text-align: center;
    float: none;
  }
}

</style>
