let table = document.getElementById('table_div')
for (let i = 0; i < table.rows.length; i++) {
    for (let j = 0; j < table.rows[i].cells.length; j++) {
        table.rows[i].cells[j].style.background = 'gray'
    }
}

tickets()

function tickets() {
    $.ajax({
        url: '/tickets/findTicketsByTicketIdIdScreeningAndStatus?screeningId=' + screening.id,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            cost = response[0]['price'];
            for (let i = 0; i < response.length; i++) {
                let numberRow = response[i]['ticketId']['row'];
                let numberSeat = response[i]['ticketId']['seat'];
                table.rows[numberRow-1].cells[numberSeat-1].style.background = 'red';
            }
        }
    })
}



let k = 0;
let tableResult = document.getElementById('tableResults');
let ticketPrice = document.getElementById('ticketPrice');
let tdNumberTickets = document.getElementById('tdNumberTickets');
let cost = screening.cost;
let ticketsList = [];
for (let i = 0; i < table.rows.length; i++) {
    for (let j = 0; j < table.rows[i].cells.length; j++) {
        table.rows[i].cells[j].addEventListener("click", function(){
            let row = i + 1;
            let col = j + 1;
            if(this.style.background === 'gray') {
                k++;
                this.style.background = 'lawngreen'
                let newRow = document.createElement("tr")
                newRow.id = row + '' + col
                let newCell = document.createElement("td")
                newCell.innerHTML = ' Ряд ' + row + ', место ' + col;
                newRow.appendChild(newCell);
                tableResult.appendChild(newRow);

                ticketsList.push({
                    ticketId: {
                        idScreening: screening.id,
                        row: row,
                        seat: col
                    },
                    price: screening.cost,
                    status: 'NOT_OVERDUE'
                })
            } else if(this.style.background === 'lawngreen') {
                k--;
                this.style.background = 'gray'
                document.getElementById(row + '' + col).remove();

                ticketsList.splice(ticketsList.indexOf({
                    ticketId: {
                        idScreening: screening.id,
                        row: row,
                        seat: col
                    },
                    price: screening.cost,
                    status: 'NOT_OVERDUE'
                }), 1)

            }
            tdNumberTickets.innerHTML = k + ' шт.';
            ticketPrice.innerHTML = k * cost + ' рублей';
        })
    }
}

function pay() {
    $.ajax({
        url: '/tickets/saveAll',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(ticketsList),
        success: function (data) {
            window.location.replace("/movies/pay");
        }
    })
}




