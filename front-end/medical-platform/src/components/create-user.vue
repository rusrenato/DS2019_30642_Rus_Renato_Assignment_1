<template>
  <div class="form">
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

      <button @click.prevent="saveUser">Save</button>

      <h1 v-if="success">User successfully created !</h1>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      token: "",
      success: false,
      user: {
        name: "",
        email: "",
        birthDate: new Date(),
        gender: "",
        address: "",
        role: "",
        caregiverName: "",
        password: "",
        rols: ["Doctor", "Caregiver", "Patient"]
      },
      caregivers: [''],
    };
  },
  methods: {
      saveUser: function() {
        const auth = {
            headers: { Authorization: "Bearer " + this.token }
        };
        console.log(auth);
        this.$http
            .post("http://localhost:8081/user", {
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
    getCaregivers: function() {
        const auth = {
        headers: { Authorization: "Bearer " + this.token }
      };
    
      this.$http
        .get('http://localhost:8081/user/caregivers', auth)
        .then(function(data) {
          console.log(data);
          var caregiversAux = [];
          data.body.forEach(function(entry){
              let caregiverObj = {name: entry.name, email: entry.email}
              caregiversAux.push(caregiverObj);
          })
          this.caregivers = caregiversAux;
          console.log(this.caregivers);
        });
    },
  },
  mounted() {
    if (localStorage.token) {
      this.token = localStorage.token;
    }
  }
};
</script>

<style scoped>
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
