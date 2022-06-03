var now = new Date();
var textoutToday;
var textoutTomorrow
var month = now.getMonth();
var date = now.getDate();

textoutToday = date;

textoutTomorrow = date + 1;

if (month===0) month=" января";
if (month===1) month=" февраля";
if (month===2) month=" марта";
if (month===3) month=" апреля";
if (month===4) month=" мая";
if (month===5) month=" июня";
if (month===6) month=" июля";
if (month===7) month=" августа";
if (month===8) month=" сентября";
if (month===9) month=" октября";
if (month===10) month =" ноября";
if (month===11) month =" декабря";

var today = document.getElementById('today_h4')
today.innerHTML = 'Сегодня, ' + textoutToday + ' ' + month
var tomorrow = document.getElementById('tomorrow_h4')
tomorrow.innerHTML = 'Завтра, ' + textoutTomorrow + ' ' + month