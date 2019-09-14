Vue.component('book-card', {
    props: ['book'],
    template: `
        <div class="alert alert-secondary">
            <b class="book-title">
                {{ book.title }}
            </b><p> with {{book.pages}} pages</p><br>
            <p>{{book.desc}}</p>
        </div>`
});


var app = new Vue({
    el: '#contents',
    data: {
        query:"",
        message:"",
        error:"",
        books: []
    },
    methods:{
        getBooks: function () {
            if (this.query){
                this.message = "Looking for some books for you";
                this.error = "";
                this.$http.get('/searchByName', {params:{name: this.query}}).then((response) => {
                    this.books = response.data;
                    this.message = "";
                    if (response.data.length === 0){
                        this.error  = "No books found for your request, please try something else";
                    }
                });
            } else {
                this.error = "Please enter something in the search line"
            }
        }
        
    }
});

