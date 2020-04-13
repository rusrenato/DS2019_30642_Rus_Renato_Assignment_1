<template>
  <div>
      <h2>Hello</h2>
        <table id="customers">
            <tr>
              <th v-for="col in columns">{{col.toUpperCase()}}</th>
            </tr>
            <tr v-for="row in tableData">
              <td v-for="col in columns">{{row[col]}}</td>
            </tr>
        </table>
    </div>

    
</template>

<script>

export default {
    data () {
        return {
            token : "",
            tableData: [],
            loggedId: "",
        }
    },
    methods: {
        logOut: function() {
            console.log('da')
            localStorage.removeItem('token');
            localStorage.removeItem('loggedId')
            this.$router.push('/login');
        }
    },

    created: function() {
        this.token = localStorage.getItem('token')
        const auth = {
            headers: { Authorization: "Bearer " + this.token }
        };
        this.loggedId = localStorage.getItem('loggedId');
        console.log(this.token + 'token');
        this.$http
            .get('http://localhost:8081/user/medicationPlan/' + this.loggedId, auth)
            .then(function(data) {
            console.log(data);
            this.tableData = data.body;
            });
    },

    computed: {
        columns: function columns() {
        if (this.tableData.length == 0) {
            return [];
        }
        console.log(this.tableData);
        console.log(this.tableData[0].medications[0].medication);
        console.log(Object.keys(this.tableData[0].medications[0]));
        console.log(Object.keys(this.tableData[0].medications[0].medication));
        return Object.keys(this.tableData[0]);
        },
        columns2: function columns2() {
            if (this.tableData.length == 0) {
            return [];
        }
        return Object.keys(this.tableData[0].medications[0]);
        },
        columns3: function columns3() {
            if (this.tableData.length == 0) {
            return [];
        }
        return Object.keys(this.tableData[0].medications[0].medication);
        }
        
    },
    mounted() {
    if (localStorage.loggedId) 
      this.loggedId = localStorage.loggedId;
    if(localStorage.token) 
      this.token = localStorage.token;
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
