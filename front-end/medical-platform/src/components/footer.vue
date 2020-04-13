<template>
  <footer>
      <p>{{copyright}}</p>
  </footer>
</template>

<script>

export default {
  data () {
    return {
       copyright: "Copyright 2019"
    }
  }
}
</script>

<style scoped>
footer{
    background: #222;
    padding: 6px;
    margin-top: 629px;
}
p{
    color:lightgreen;
    text-align: center;
}
</style>
