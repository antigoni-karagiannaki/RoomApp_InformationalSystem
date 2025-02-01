

function loadBook(ChosenBook){
    document.getElementById('start').style.display='none';
    document.getElementById('header').innerText =ChosenBook;
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const obj = JSON.parse(xhr.responseText);
            console.log(obj);
            document.getElementById('Chosebook').style.display = 'block';
            document.getElementById("img").src = obj.photo;
            document.getElementById("isbn").innerText = "Isbn: "+ obj.isbn;
            document.getElementById("title").innerText = "Title: "+ obj.title;
            document.getElementById("authors").innerText = "Authors: "+ obj.authors;
            document.getElementById("genre").innerText = "Genre: "+ obj.genre;
            document.getElementById("pages").innerText = "Pages: "+ obj.pages;
            document.getElementById("publicationyear").innerText = "Publication Year: "+ obj.publicationyear;
            document.getElementById("url").href = obj.url;

        }
    };
    
    xhr.open('POST', 'BookServlet?title='+ChosenBook);
    xhr.send();
    
}

function go_back(){
    document.getElementById('header').innerText ="BOOKS";
    document.getElementById('start').style.display='block';
    document.getElementById('Chosebook').style.display = 'none';

    
}








function Available(){
    var isbn = document.getElementById("isbn").innerText;
    isbn = isbn.split('Isbn: ').join("");
    console.log(isbn);
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Η αλλαγή πραγματοποιήθηκε!");
            
            
        }
    };
    xhr.open('POST', 'AvailabilityServlet?isbn='+isbn);
    xhr.send();
}

function CheckBorrowing(){
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
            tmp = tmp.split('user_id').join("ID χρήστη");
            tmp = tmp.split('fromdate').join("Άπο");
            tmp = tmp.split('todate').join("Μέχρι"); 
            tmp = tmp.split('status').join("Kατάσταση");
            
            console.log(tmp);
            document.getElementById("Borrowing").innerText = tmp;
    
        }
    };
    xhr.open('POST', 'RequestForBorrowingServlet');
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

function Exit(){
    
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
                window.location.href = "http://localhost:8080/ask3/";
        }
    };
    
    xhr.open('POST', 'CookieServlet?use=Logout');
    xhr.send();
}


function Find_User_Info_From_Borrowed_Books(){

    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
        }
    };
    xhr.open('POST', 'CheckBorrowedBooksServlet');
    xhr.send();
}