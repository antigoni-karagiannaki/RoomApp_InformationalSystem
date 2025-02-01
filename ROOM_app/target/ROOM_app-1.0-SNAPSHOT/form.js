const pass1 = document.getElementById('password');
const pass2 = document.getElementById('pass2');
const error = document.getElementById('error');
const form = document.getElementById('form');

form.addEventListener('submit',(e)=>{
  let messages =[]
  if(pass1.value != pass2.value){
    messages.push('οι κωδικοί δεν ταιριάζουν!!!')
    e.preventDefault()
    error.innerText = messages.join(', ')
  }                                                          
});

//i check for Password Mismatch sub question(a)
function Show_Password(){
 var Pass = document.getElementById("password");
 if(Pass.type === 'password'){
     Pass.type = "text";
 }else if(Pass.type === 'text'){
     Pass.type = "password";
 }
    
}

function Show_Check_Password(){
  var Pass = document.getElementById("pass2");
  if(Pass.type === 'password'){
    Pass.type = "text";
  }else if(Pass.type === 'text'){
    Pass.type = "password";
  }
}   

let timeout;
let password = document.getElementById('password');
let username = document.getElementById('username');
let username_error = document.getElementById('username_error');

let strengthBadge = document.getElementById('StrengthDisp');
let strongPassword = new RegExp('(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])');  


username.addEventListener("input", () => {
  username_error.style.display= 'block'
  clearTimeout(timeout);
  timeout = setTimeout(() => UsernameChecker(username.value), 500);
  if(password.value.length !== 0){
    username_error.style.display != 'block'
  } else{
    username_error.style.display = 'none'
  }
});

password.addEventListener("input", () => {
  strengthBadge.style.display= 'block'
  clearTimeout(timeout);
  timeout = setTimeout(() => StrengthChecker(password.value), 500);
  if(password.value.length !== 0){
    strengthBadge.style.display != 'block'
  } else{
    strengthBadge.style.display = 'none'
 }
});


function StrengthChecker(PasswordParameter){
  var count =0;
if(strongPassword.test(PasswordParameter)) {
  strengthBadge.style.color ='green';
  strengthBadge.textContent = 'Password is: Strong'; 
}else{
  strengthBadge.style.color ='blue';
  strengthBadge.textContent = 'Password is: Medium'; 
}

for (let i = 0; i < PasswordParameter.length; i++) {
  if(/[0-9]/.test(PasswordParameter[i])){
    count ++;
  }else{
    count--;
  }
}
if(count > 0){
  strengthBadge.style.color ='red';
  strengthBadge.textContent = 'Password is: weak'; 
}

}

function registerPOST(){
    let myForm = document.getElementById('form');
    let formData = new FormData(myForm);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));
    var jsonData=JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.href = "http://localhost:8080/CS351_web_app/";
            alert('User created!!!');
        } else if (xhr.status !== 200) {
            alert(xhr.responseText+" already exist!!!");
        }
    };
    console.log(jsonData);
    xhr.open('POST', 'RegistrationServlet');
    xhr.send(jsonData);
}

function go_back(){
   window.location.href="http://localhost:8080/CS351_web_app/";
}