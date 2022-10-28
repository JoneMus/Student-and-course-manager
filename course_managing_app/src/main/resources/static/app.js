const formAS = document.querySelector('.formAS');
const formASTC = document.querySelector('.formASTC');
const formAC = document.querySelector('.formAC')

// Käytetään fechiä opiskelijoiden lisäämiseen
formAS.addEventListener('submit', event => { 
    event.preventDefault();

        const formData = new FormData(formAS);
        const data = Object.fromEntries(formData);

        fetch('http://localhost:8080/addStudent', {
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(data)
                
        })
        .then(res => console.log(res))
        .catch(error => console.log(error))
})

// Lisätään opiskelija kurssille
formASTC.addEventListener('submit', event => {
    event.preventDefault();

    const formData = new FormData(formASTC);
    const data = Object.fromEntries(formData);

    fetch('http://localhost:8080/addStudentToCourse', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(res => console.log(res))
    .catch(error => console.log(error))
})

// Lisätään kurssi
formAC.addEventListener('submit', event => {
    event.preventDefault();

    const formData = new FormData(formAC);
    const data = Object.fromEntries(formData);

    fetch('http://localhost:8080/addCourse', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(res => console.log(res))
    .catch(error => console.log(error))
})