<template>
  <div>
      <h2>Hello</h2>
        <table id="customers">
          <thead>
            <tr>
              <th v-for="col in columns">{{col.toUpperCase()}}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="row in tableData">
              <td v-for="col in columns">{{row[col]}}</td>
            </tr>
          </tbody>
        </table>

        <button @click.prevent="logOut">Logout</button>

    </div>

    
</template>

<script>

export default {
    data () {
        return {
            token : "",
            tableData: [],
            loggedUser : {},
            loggedId: "",
            userRole: "",
        }
    },
    methods: {
        logOut: function() {
        console.log('da')
        localStorage.removeItem('token');
        localStorage.removeItem('loggedId');
        localStorage.removeItem('userRole');
        this.$router.push('/login');
        }
    },

    created: function() {
        this.token = localStorage.getItem('token')
        const auth = {
            headers: { Authorization: "Bearer " + this.token }
        };
        console.log(auth);
        this.loggedId = localStorage.getItem('loggedId');
        
        console.log(this.token + 'token');
        this.$http
            .get('http://localhost:8081/user/caregiver/patients/' + this.loggedId, auth)
            .then(function(data) {
            console.log(data);
            this.tableData = data.body
            });
    },

    computed: {
        columns: function columns() {
        console.log(this.loggedId + 'hihi');
        if (this.tableData.length == 0) {
            return [];
        }
        
        return Object.keys(this.tableData[0]);
        },
        
    },
    mounted() {
    if (localStorage.loggedId) 
      this.loggedId = localStorage.loggedId;
    if(localStorage.token) 
      this.token = localStorage.token;
    if(localStorage.userRole)
      this.userRole = localStorage.userRole;
    },
}
</script>

<style scoped>
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 50%;
  margin: 30px 30px 200px 350px;
}

#customers td,
#customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even) {
  background-color: #f2f2f2;
  margin: 0px 10px 10px 10px;
}

#customers tr:hover {
  background-color: #ddd;
}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4caf50;
  color: white;
}

button {
  width: 100%;
  background-color: #4caf50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>
