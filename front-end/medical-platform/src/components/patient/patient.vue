<template>
  <div>
    <div class="sidebar">
      <p :class="className1" @click="changeMenu('create')">Account details</p>
      <p :class="className2" @click="changeMenu('view')">Medication plans</p>
      <p @click="logout">Logout</p>
    </div>

    <account-details v-if="show == 'create'"></account-details>
    <medication-plans v-if="show == 'view'"></medication-plans>

  </div>
</template>

<script>
import accountDetails from  './account-details'
import medicationPlans from './medication-plans'

export default {
  components: {
    'account-details' : accountDetails,
    'medication-plans' : medicationPlans,
  },
  data() {
    return {
      token : "",
      loggedId: "",
      show: "create",
      className1: "active",
      className2: "",
      userRole: "",
    };
  },
  methods: {
    changeMenu: function(name) {
      this.show = name;
        switch (name) {
          case "create":
            this.className1 = "active";
            this.className2 = "";
            break;
          case "view":
            this.className1 = "";
            this.className2 = "active";
            break;
          }
    },
    logout: function() {
      localStorage.removeItem('token');
      localStorage.removeItem('loggedUser');
      localStorage.removeItem('userRole');
      this.$router.push('/login');
    }
  },
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
