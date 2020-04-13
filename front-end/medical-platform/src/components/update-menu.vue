<template>
  <div>
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
    </div>
    
    <div id = "chooseUserName">
      <label>Update :</label>
      <select class="" id="user" name="User" v-model="selectedUser" @click="updateFields">
        <option value selected disabled hidden>Choose caregiver</option>
        <option v-for="optionName in tableData" :value="optionName">{{optionName.name}}</option>
      </select>
    </div>

    <div v-if="selectedUser.name != undefined">
    <div class="form" v-if="conditon">
      <form>
        <div id="inputs">
          <label for="name">Name</label>
          <input class="input" type="text" id="name" placeholder="Name" v-model="user.name" />

          <label for="email">Email</label>
          <input class="input" type="email" id="email" placeholder="Email" v-model="user.email" />

          <label for="password">Password</label>
          <input class="input" type="password" id="password" placeholder="Password" v-model="user.password"/>

          <div>
            <label for="address">Address</label>
            <input class="input" type="text" id="address" placeholder="Address" v-model="user.address"/>
          </div>
        </div>

        <label for="role">Role</label>
        <select class="input" id="role" name="Role" v-model="user.role">
          <option value selected disabled hidden>Choose role</option>
          <option value="Caregiver">Caregiver</option>
          <option value="Patient">Patient</option>
        </select>

        <div v-if="user.role=='Patient'">
          <label for="caregiver">Caregiver</label>
          <select class="input" id="caregiver" name="Caregiver" v-model="user.caregiverName" @click="getCaregivers">
            <option value selected disabled hidden>Choose caregiver</option>
            <option v-for="caregiver in caregivers" :value="caregiver">{{caregiver.name}}</option>
          </select>
        </div>

        <label for="BirthDate">Birthdate</label>

        <datetime v-model="user.BirthDate" id="BirthDate" class="datetime"></datetime>

        <div id="radios">
          <label>Gender:</label>
          <label>Male</label>
          <input type="radio" value="Male" v-model="user.gender" />
          <label>Female</label>
          <input type="radio" value="Female" v-model="user.gender" />
        </div>
        <button @click.prevent="updateUser">Update</button>
    </form>
  </div>
    

    <div v-if="selectedOption == 'medication'">
      <div class="form">
      <form>
        <div id="inputs">
          <label for="name">Name</label>
          <input class="input" type="text" id="name" placeholder="Name" v-model="medication.name" />

          <label for="sideEffects">Side Effects</label>
          <input class="input" type="text" id="sideEffects" placeholder="Side Effects" v-model="medication.sideEffects" />

          <label for="dosage">Dosage</label>
          <input class="input" type="text" id="dosage" placeholder="Dosage" v-model="medication.dosage"/>

        <button @click.prevent="saveMedication">Update</button>
        </div>
      </form>
      </div>
    </div>

    </div>

  </div>
</template>

<script>



export default {
  
  
  data () {
    return {
      token : "",
      selectedOption : "",
      selectedUser : {},
      id: "",
      tableData: [],
      optionNames: [],
      user: {
        name: "",
        id : "",
        email: "",
        birthDate: new Date(),
        gender: "",
        address: "",
        role: "",
        caregiverName: "",
        password: "",
        rols: ["Doctor", "Caregiver", "Patient"]
      },
      medication: {
        name: "",
        sideEffects: "",
        dosage: "",
      },
    }
  },
  methods: {
     loadDataAbout: function(selectedOption) {
       console.log(this.selectedUser);
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
    updateFields: function() {
      console.log(this.selectedUser);
      console.log(this.selectedUser.rol);
      if(this.selectedOption == 'caregivers' || this.selectedOption == 'patients') {
        this.user.name = this.selectedUser.name;
        this.user.email = this.selectedUser.email;
        this.user.password = this.selectedUser.password;
        this.user.address = this.selectedUser.address;
        this.user.role = this.selectedUser.rol;
        this.user.birthDate = this.selectedUser.birthDate;
        this.user.gender = this.selectedUser.gender;
        this.user.id = this.selectedUser.id;
      }
    },
    updateUser: function() {
      const auth = {
            headers: { Authorization: "Bearer " + this.token }
        };
        console.log(auth);
        this.$http
            .put("http://localhost:8081/user/" + this.user.id, {
            name: this.user.name,
            email: this.user.email,
            address: this.user.address,
            birthDate: this.user.birthDate,
            gender: this.user.gender,
            rol: this.user.rols.indexOf(this.user.role),
            password: this.user.password,
            caregiver : this.user.caregiverName.email,
            }, auth)
            .then(function(data) {
            this.success = true;
            });
    },
  },
  computed: {
    columns: function columns() {
      if (this.tableData.length == 0) {
        return [];
      }
      return Object.keys(this.tableData[0]);
    },
    conditon(){
      return this.selectedOption == 'caregivers' || this.selectedOption == 'patients';
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
   margin: 30px 500px 50px 350px;
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

#chooseUserName{
  margin-left: 350px;
  size : 30%;
}

.form {
  border-radius: 5px;
  background-color: lightgray;
  padding: 20px;
  margin: 30px 900px 30px 250px;
}

.input {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.input2 {
  width: 50%;
  padding: 12px 20px;
  margin: 50px 315px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

#radios {
  display: inline-block;
  margin-right: 10px;
}

.datetime {
  margin: 10px 10px 10px 0px;
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

h1{
  color: #4caf50;
}
</style>
