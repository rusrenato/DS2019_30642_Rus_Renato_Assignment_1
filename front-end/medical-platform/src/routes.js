import login from './components/login.vue'
import doctor from './components/doctor.vue'
import patient from './components/patient/patient.vue'
import caregiver from './components/caregiver.vue'

export default [
    {   
        path: '/login',
        name: 'Login',
        component: login, 
       
    },
    {  
         path: '/doctor',
         component: doctor,
         beforeEnter:(to,from,next) => {
            if(localStorage.getItem('userRole') == 'Doctor'){
                next();
            } else {
                alert('Yo madafaka, go back and login');
                next({ name: 'Login'})
            }
        }
    },

    {
        path: '/patient', 
        component: patient,
        beforeEnter:(to,from,next) => {
            if(localStorage.getItem('userRole') == 'Patient'){
                next();
            } else {
                alert('Yo madafaka, go back and login');
                next({ name: 'Login'})
            }
        }
    },
    {
        path: '/caregiver',
        component: caregiver,
        beforeEnter:(to,from,next) => {
            if(localStorage.getItem('userRole') == 'Caregiver'){
                next();
            } else {
                alert('Yo madafaka, go back and login');
                next({ name: 'Login'})
            }
        }
    },
]
