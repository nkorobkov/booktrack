Vue.component('book-card', {
    props: ['book'],
    template: `
        <div class="alert alert-secondary">
            <b class="book-title">
               <a v-bind:href="'/book/'+ book.id">{{ book.title }}</a> 
            </b><p> with {{book.pages}} pages</p>
            <p  v-if="book.desr"><br>{{book.desc}}</p>
        </div>`
});


var app = new Vue({
    el: '#contents',
    data: {
        query: "",
        message: "",
        error: "",
        books: []
    },
    methods: {
        getBooks: function () {
            if (this.query) {
                this.message = "Looking for some books for you";
                this.error = "";
                this.$http.get('/searchByName', {params: {name: this.query}}).then((response) => {
                    this.books = response.data;
                    this.message = "";
                    if (response.data.length === 0) {
                        this.error = "No books found for your request, please try something else";
                    }
                }, (reason) => {
                    if (reason.status === 429) {
                        this.error = "Too many requests from your IP. Please wait just a little and try again";
                    } else {
                        this.error = "We are very sory, but somehting unexpected happend." +
                            "Please try again in some time or consider using https://www.readinglength.com as an alternative";
                    }
                    this.message = "";
                });
            } else {
                this.error = "Please enter something in the search line"
            }
        }

    }
});

