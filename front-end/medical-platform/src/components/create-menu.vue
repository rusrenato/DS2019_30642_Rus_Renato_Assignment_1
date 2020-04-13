<template>
    <div>
        <div class="select">
        <label for="role">Create : </label>
        <select class="input" id="role" name="Role" v-model="category">
            <option value selected disabled hidden>Choose category</option>
            <option value="user">User</option>
            <option value="medication">Medication</option>
            <option value="medication-plan">Medication Plan</option>
        </select>
        </div>

        <create-user-form v-if="this.category=='user'"></create-user-form>
        <create-medication-form v-if="this.category=='medication'"></create-medication-form>
        <create-medication-plan-form v-if="this.category=='medication-plan'"></create-medication-plan-form>
        
    </div>
</template>

<script>
import createUser from './create-user'
import createMedication from './create-medication'
import createMedicationPlan from './create-medication-plan'
export default {
  components : {
    "create-user-form" : createUser,
    "create-medication-form" : createMedication,
    "create-medication-plan-form" : createMedicationPlan
  },
  data() {
    return {
        category: "",
    };
  },
  methods: {
      show: function() {
          console.log(this.x);
      }
  },
};
</script>

<style scoped>
.input {
  width: 50%;
  padding: 12px 20px;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  
  box-sizing: border-box;
}

.select {
    margin-left : 255px;
}
</style>
