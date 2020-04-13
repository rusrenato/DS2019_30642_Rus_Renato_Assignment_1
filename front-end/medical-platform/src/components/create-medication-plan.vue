<template>
  <div class="form">
    <form>
      <div id="inputs">

        <label for="patients">Patients</label>
        <select class="input" id="patients" name="Patients" v-model="selectedPatient" @click="getPatients">
          <option value selected disabled hidden>Choose patient</option>
          <option v-for="patient in patients" :value="patient">{{patient.name}}</option>
        </select>
       
        <form>

        <label for="medication">Medication</label>
        <select class="input" id="medication" name="Medication" v-model="selectedMedication" @click="getMedications">
          <option value selected disabled hidden>Choose medications</option>
          <option v-for="medication in medications" :value="medication">{{medication.name}}</option>
        </select>

        <label for="intake">Intake intervals</label>
        <input class="input" type="text" id="intake" placeholder="Intake intervals" v-model="intake"/>

         <button @click.prevent="addMedicationIntervals">Add</button>

        <ul>
            <li v-for="medicationAndIntervalToShow in medicationAndIntervalsToShow">{{medicationAndIntervalToShow.name}} - {{medicationAndIntervalToShow.intervals}} /day</li>
        </ul>

       <div>
            <label for="periodOfTreatment">Period of Treatment</label>
            <input  class="input" type="text" id="periodOfTreatment" placeholder="Period of Treatment" v-model="periodOfTreatment"/>
       </div>

        </form>

      <button @click.prevent="createMedicationPlan">Save</button>

       <h1 v-if="success">Medication Plan successfully created !</h1>
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
      periodOfTreatment: "",
      selectedPatient : "",
      intake : "",
      selectedMedication : "",
      patients: [''],
      medications: [''],
      medicationAndIntervalsToSend: [],
      medicationAndIntervalsToShow: [],
    };
  },
  methods: {
      getPatients: function() {
        const auth = {
        headers: { Authorization: "Bearer " + this.token }
      };

      this.$http
        .get('http://localhost:8081/user/patients', auth)
        .then(function(data) {
          console.log(data);
          this.patients = data.body
        });
    },
     getMedications: function() {
        const auth = {
        headers: { Authorization: "Bearer " + this.token }
      };
    
      this.$http
        .get('http://localhost:8081/medication', auth)
        .then(function(data) {
          console.log(data);
          this.medications = data.body
        });
    },
     addMedicationIntervals: function() {
         let createdObjtoSend= {medicationId : this.selectedMedication.id, intakeIntervals: parseInt(this.intake,10) }
         let createdObjToShow= {name : this.selectedMedication.name, intervals: this.intake}
         this.medicationAndIntervalsToSend.push(createdObjtoSend);
         this.medicationAndIntervalsToShow.push(createdObjToShow);
         console.log(this.medicationAndIntervalsToSend); 
         console.log(this.medicationAndIntervalsToShow); 
     },
     createMedicationPlan: function() {
        const auth = {
            headers: { Authorization: "Bearer " + this.token }
        };

        let obj = {
            'periodOfTreatment' : this.periodOfTreatment,
            'userId' : this.selectedPatient.id,
            'medicationIntervals' : this.medicationAndIntervalsToSend
        }

        console.log(obj)

        this.$http.post('http://localhost:8081/medicationPlan', obj, auth).then(function(data) {
             console.log(data);
             this.success = true;
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
