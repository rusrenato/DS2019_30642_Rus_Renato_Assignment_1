<template>
   <div class="table">
      <select class="input" id="role" name="Role" v-model="selectedOption" @click="loadDataAbout(selectedOption)">
        <option value="none" selected disabled hidden>Please choose</option>
        <option value="caregivers">Caregivers</option>
        <option value="patients">Patients</option>
        <option value="medication">Medications</option>
      </select>

      <div>
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
      </div>

      <div id="delete">
      <input class="input" type="text" v-model="id" placeholder="Id">
      <button @click.prevent="deleteData(id)">Delete</button>

      
      </div>
    </div>
</template>

<script>

export default {
  data () {
    return {
       token : "",
       selectedOption : "",
       id: "",
       tableData: [],
       succes : false,
    }
  },
  methods: {
     loadDataAbout: function(selectedOption) {
      const auth = {
        headers: { Authorization: "Bearer " + this.token }
      };
      if(selectedOption != "") {
        var path = (selectedOption == "caregivers" || selectedOption == "patients") ? ("user/" + selectedOption) : "medication";
        this.$http
          .get("http://localhost:8081/" + path, auth).then(function(data) {
            this.tableData = data.body;
            if(selectedOption == "caregivers"){
              this.tableData.forEach(function(entry){
                delete entry.caregiver;
              })
            }
          });
      }
    },

    deleteData: function(id) {
      const auth = {
        headers: { Authorization: "Bearer " + this.token }
      };

      var path = (this.selectedOption == "caregivers" || this.selectedOption == "patients") ? ("user/") : "medication/";
       
      this.$http.delete("http://localhost:8081/" + path + this.id).then(function(data){
         this.loadDataAbout(this.selectedOption);
         this.id="";
      })
    }

  },
  computed: {
    columns: function columns() {
      if (this.tableData.length == 0) {
        return [];
      }
      return Object.keys(this.tableData[0]);
    }
  },
   mounted() {
    if (localStorage.token) {
      this.token = localStorage.token;
    }
  },
}
</script>

<style scoped>
.table{
   margin: 30px 30px 200px 350px;
}

.input {
  width: 50%;
  padding: 12px 20px;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

/*-------------------- */

#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 50%;
  margin-top: 5%
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

#delete {
  display: inline-block;
  margin-top: 2%;
}

button {
  width: 30%;
  background-color: #4caf50;
  color: white;
  padding: 14px 20px;
  margin: 8px 5px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}



</style>
