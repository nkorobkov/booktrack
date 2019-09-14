Vue.component('book-card', {
    props: ['book'],
    template: `<div class="book">
        <div class="book-body">
            <b class="book-title">
                {{ book.title }}
            </b><p> with {{book.pages}} pages</p><br>
            <p>{{book.desc}}</p>
        </div>
        </div>`
});


var app = new Vue({
    el: '#contents',
    data: {
        query:"",
        books: []
    },
    methods:{
        getBooks: function () {
            console.log('getBooksCalled');
            this.$http.get('/searchByName', {params:{name: this.query}}).then((response) => {
                this.books = response.data;
            });
        }
        
    }
});

