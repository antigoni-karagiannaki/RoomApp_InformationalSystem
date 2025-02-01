function loadRoom(Chosen){
    document.getElementById('start').style.display='none';
    document.getElementById('header').innerText =Chosen;
    console.log("chosen" +Chosen.toString());
    
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const obj = JSON.parse(xhr.responseText);
            document.getElementById('Chose').style.display = 'block';
            document.getElementById("img").src = obj.photo;
            document.getElementById("id_room").innerText = "Κωδικός δωματίου: "+ obj.id_room;
            console.log("this is an object "+obj.room_name);
            document.getElementById("room_name").innerText = "Όνομα δωματίου: "+ obj.room_name;
            document.getElementById("floor").innerText = "Όροφος: "+ obj.floor;
            document.getElementById("capacity").innerText = "Χωρητικότητα: "+ obj.capacity;
            document.getElementById("address").innerText = "Διεύθυνση: "+ obj.address;
            document.getElementById("type").innerText = "Τύπος: "+ obj.type;
            document.getElementById("facilities").innerText = "Παροχές: "+ obj.facilities;
            document.getElementById("price").innerText = "Τιμή: "+ obj.price;
            console.log(obj);

        }
    };
    
    xhr.open('POST', 'RoomServlet?room_name='+Chosen);
    xhr.send();
    
}

function go_back(){
    document.getElementById('header').innerText ="BOOKS";
    document.getElementById('start').style.display='block';
    document.getElementById('Chosebook').style.display = 'none';
    document.getElementById('reviewbook').style.display='none';
    document.getElementById('assessment').style.display='none';
    document.getElementById('but_review').style.display='none';
    document.getElementById('but_order').style.display='none';
    document.getElementById('Chosen_library').style.display='none';
    document.getElementById('Books').innerText = "";
    document.getElementById('Hide_right').style.display='none';
    document.getElementById('Hide_borrowed').style.display='block';
}


function Availability(){
    var isbn = document.getElementById("isbn").innerText;
    isbn = isbn.split('Isbn: ').join("");

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var temp = xhr.responseText;
            temp = temp.split('"').join("");
            temp = temp.split('}{').join("\r\n\n");
            temp = temp.split('{').join("");    
            temp = temp.split('}').join("");
            temp = temp.split(',').join(" , ");
            
            temp = temp.split('libraryname').join("Όνομα βιβλιοθήκης");
            temp = temp.split('city').join("Πόλη");
            temp = temp.split('address').join("Διεύθυνση");
            temp = temp.split('libraryinfo').join("Πληροφορίες βιβλιοθήκης"); 
            temp = temp.split('telephone').join("Τηλέφωνο");
            temp = temp.split('library_id').join("ID Βιβλιοθήκη");
            document.getElementById('Books').innerText = temp;
            document.getElementById('Hide_right').style.display='block';
            document.getElementById('Hide_borrowed').style.display='none';
            
            
        }
    };
    xhr.open('POST', 'Check_In_LibrariesServlet?isbn='+isbn);
    xhr.send();
}



function ChangeStatus(){
    var id= document.getElementById('id').value;
    var status;
        if( document.getElementById('borrowed').checked){
            status = document.getElementById('borrowed').value;
        }else{
            status = document.getElementById('successEnd').value;
        }
        
    console.log(id);
     console.log(status);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if(xhr.responseText === "borrowed"){
                alert("Το βιβλίο δανείστηκε!");
            }else{
                alert("Το βιβλίο επιστράφηκε!");
            }
            CheckBorrowing();
        }else if(xhr.status === 403){
            alert("Αποτυχία δανεισμού!");
        }
    };
    xhr.open('POST', 'ChangeStatusServlet?id='+id+"&status="+status);
    xhr.send();
}



var search="capacity<='";
function tmp(value){

     if(value === '0'){
         document.getElementById('tmp').innerHTML = "Χωρητικότητα :";
         search="capacity<='";
    }else if(value === '1'){
        document.getElementById('tmp').innerHTML = "Διαθεσιμότητα :";
        search="status='";
    }else if(value === '2'){
        search="type='";
        document.getElementById('tmp').innerHTML = "Τύπο :";
    }
    
}

function QuirkySearch(){ 
    var value = document.getElementById('value').value;
 
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if (xhr.responseText === "capacity") {
                console.log("heayaaa");
                document.getElementById('Rooms').innerText = "Δεν βρεθηκε αίθουσα με " + value + "χωρητικότητα";
            } else if (xhr.responseText === "status") {
                document.getElementById('Rooms').innerText = "Δεν βρεθηκε αίθουσα με κατάσταση: " + value;
            } else if (xhr.responseText === "type") {
                document.getElementById('Rooms').innerText = "Δεν βρεθηκε αίθουσα με τύπο: " + value;
            } else {
                var temp = xhr.responseText;
                temp = temp.split('"').join("");
                temp = temp.split('}{').join("\r\n\n");
                temp = temp.split('{').join("");
                temp = temp.split('}').join("");
                temp = temp.split(',').join(" , ");
                temp = temp.split('id_room').join("Κωδικός δωματίου");
                temp = temp.split('room_name').join("Όνομα δωματίου");
                temp = temp.split('floor').join("Όροφος");
                temp = temp.split('capacity').join("Χωρητικότητα");
                temp = temp.split('address').join("Διεύθυνση");
                temp = temp.split('type').join("Τύπος");
                temp = temp.split('facilities').join("Παροχές");
                temp = temp.split('price').join("Τιμή");
                document.getElementById('Rooms').innerText = temp;
                document.getElementById('Hide_right').style.display = 'block';
            }
        }
        
    };
    
    xhr.open('POST', 'Rooms_from_Database?value='+value+'&search='+search);
    xhr.send();
}


function Show(op){
    
    if(op === 1){
        document.getElementById('but_order').style.display='none';
        document.getElementById('Chosen_library').style.display='none';
        document.getElementById('reviewbook').style.display='block'; 
        document.getElementById('change1').innerText ="Αξιολόγηση:";
        document.getElementById('change2').innerText ="Βαθμολόγηση:";
        document.getElementById('assessment').style.display='block';
        document.getElementById('but_review').style.display='block';
        document.getElementById('Hide_borrowed').style.display='none';
    }else if(op === 2){
        document.getElementById('assessment').style.display='none';
        document.getElementById('but_review').style.display='none';
        document.getElementById('reviewbook').style.display='block'; 
        document.getElementById('change1').innerText ="Isbn";
        document.getElementById('change2').innerText ="ID Βιβλιοθήκη";
        document.getElementById('but_order').style.display='block';
        document.getElementById('Chosen_library').style.display='block';
        document.getElementById('Hide_borrowed').style.display='none';
        Availability();
        
    }
}

function Submit(){
    var grade = document.getElementById('grade').value;
    var assessment = document.getElementById('assessment').value;
    var isbn = document.getElementById("isbn").innerText;
    isbn = isbn.split('Isbn: ').join("");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('reviewbook').style.display='none'; 
            if(xhr.responseText === "OK"){
                alert("Το σχόλιο αποθηκεύτηκε!");
            }else{
                alert("Δεν έχεις διαβάσει το βιβλίο,άρα δεν μπορείς να το σχολιάσεις");
            }
        }
    };
    
    xhr.open('POST', 'ReviewServlet?grade='+grade+'&assessment='+assessment+'&isbn='+isbn);
    xhr.send();


}





function Order_Book(){
    var isbn = document.getElementById("Chosen_library").value;
    var library_id = document.getElementById("grade").value;

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if(xhr.responseText =="1"){
            alert("Το βιβλειο καρτηθηκε!");
            }else if(xhr.responseText =="-1"){
                alert("Λάθος isbn ή βιβλιοθήκη ή το βιβλίο δεν είναι διαθέσιμο!");
            }
        }
    };
    xhr.open('POST', 'Borrow_BookServlet?isbn='+isbn+'&id='+library_id+"&date="+date);
    xhr.send();
}

function Borrowed_Books(){


    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var tmp = xhr.responseText;
            tmp = tmp.split('"').join("");
            tmp = tmp.split('}{').join("\r\n\n");
            tmp = tmp.split('{').join("");    
            tmp = tmp.split('}').join("");
            tmp = tmp.split(',').join(" , ");
            tmp = tmp.split('borrowing_id').join("Αρ.Δανεισμού");
            tmp = tmp.split('title').join("Τίτλος");
            tmp = tmp.split('todate').join("Ημερομηνία λήξης δανεισμού");
            document.getElementById('borrowed_books').innerText = tmp;
        }
    };
    xhr.open('POST', 'Borrowed_BooksServlet');
    xhr.send();
}

function return_book(){
    var borrowing_id = document.getElementById('borr_id').value;

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Το βιβλίο έχει επιστραφεί!");
        }
    };
    xhr.open('POST', 'Return_bookServlet?borrowing_id='+borrowing_id);
    xhr.send();
}