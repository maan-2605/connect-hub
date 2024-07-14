// scripts.js

document.addEventListener('DOMContentLoaded', function() {
    // Daily Views Chart
    var dailyViewsData = /*[[${dailyViews}]]*/ {};
    var dailyViewsCtx = document.getElementById('dailyViewsChart').getContext('2d');
    new Chart(dailyViewsCtx, {
        type: 'line',
        data: {
            labels: Object.keys(dailyViewsData),
            datasets: [{
                label: 'Daily Views',
                data: Object.values(dailyViewsData),
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    // Country Views Chart
    var countryViewsData = /*[[${viewsByCountry}]]*/ {};
    var countryViewsCtx = document.getElementById('countryViewsChart').getContext('2d');
    new Chart(countryViewsCtx, {
        type: 'pie',
        data: {
            labels: Object.keys(countryViewsData),
            datasets: [{
                data: Object.values(countryViewsData),
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(153, 102, 255)'
                ]
            }]
        },
        options: {
            responsive: true
        }
    });
});
