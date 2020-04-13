<template>
   <div class="sidebar">
        <p class="active">Create</p>
        <p :class="var1" @click="var1= 'active'">View</p>
        <p @click="show = 'update'">Update</p>
        <p @click="show = 'delete'">Delete</p>
    </div>

    
</template>

<script>

export default {
  props: ['var1'],
  data () {
    return {
      
    }
  },
  methods : {
      show : function() {
          
      }
      
  }
}
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
  background-color: #4CAF50;
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
  .sidebar p {float: left;}
  div.content {margin-left: 0;}
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
  .sidebar p {
    text-align: center;
    float: none;
  }
}
</style>
