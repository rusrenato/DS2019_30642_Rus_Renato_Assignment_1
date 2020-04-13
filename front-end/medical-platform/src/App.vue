<template>
<div>
  <router-view></router-view>
</div>
 
</template>

<script>
import Login from './components/login'
import Footer from './components/footer'
import Doctor from './components/doctor'
import Patient from './components/patient/patient'
import Caregiver from './components/caregiver'

export default {
  name: 'app',
  components:{
    'app-login': Login,
    'app-footer': Footer,
    'doctor' : Doctor,
    'patient' : Patient,
    'caregiver' : Caregiver,
  },
  data () {
    return {
      
    }
  }
}
</script>

<style scoped>
  body{
    color:red;
  }
</style>
