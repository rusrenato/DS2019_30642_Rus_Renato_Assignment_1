<template>
  <div class="form">
    <form>
      <div id="inputs">
        <label for="name">Name</label>
        <input class="input" type="text" id="name" placeholder="Name" v-model="medication.name" />

        <label for="sideEffects">Side Effects</label>
        <input class="input" type="text" id="sideEffects" placeholder="Side Effects" v-model="medication.sideEffects" />

        <label for="dosage">Dosage</label>
        <input class="input" type="text" id="dosage" placeholder="Dosage" v-model="medication.dosage"/>

      <button @click.prevent="saveMedication">Save</button>

       <h1 v-if="success">Medication successfully created !</h1>
       <h1 v-if="!success">Medication already exists !</h1>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      token: "",
      success: undefined,
      medication: {
        name: "",
        sideEffects: "",
        dosage: "",
      },
      caregivers: [''],
    };
  },
  methods: {
        saveMedication: function() {
            const auth = {
                headers: { Authorization: "Bearer " + this.token }
            };
            console.log(auth);
            this.$http
                .post("http://localhost:8081/medication", {
                name: this.medication.name,
                sideEffects: this.medication.sideEffects,
                dosage: this.medication.dosage,
                }, auth)
                .then(function(data) {
                console.log(data);
                this.success = true;
                this.clear();
                });
                console.log(this.succes)
                console.log(this.succes != true)
            if(this.succes != true) {
                this.succes = false;
            }
            console.log(this.succes);
            
        },
        clear: function() {
            this.medication.name= "";
            this.medication.sideEffects= "";
            this.medication.dosage= "";
        }
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
