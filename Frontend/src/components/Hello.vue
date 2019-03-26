<template>
    <div class="masthead">
        <div class="container d-flex h-100 align-items-center">
            <div class="mx-auto text-center">
                <h1>Co-operator</h1>
                <h2>An ECSE 321 project</h2>
                <a href="/#/list" class="btn btn-primary">Get Started</a>
            </div>
        </div>
    </div>

</template>


<script>
export default {
  name: 'hello',
  created() {
    console.log(this);
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<style scoped>
    .masthead {
        position: relative;
        width: 100%;
        height: auto;
        min-height: 35rem;
        padding: 15rem 0;
        color: #ffffff;
        background: -webkit-gradient(linear, left top, left bottom, from(rgba(22, 22, 22, 0.1)), color-stop(75%, rgba(22, 22, 22, 0.5)), to(#161616)), url("../assets/img/bg-masthead.jpg");
        background: linear-gradient(to bottom, rgba(22, 22, 22, 0.1) 0%, rgba(22, 22, 22, 0.5) 75%, #161616 100%), url("../assets/img/bg-masthead.jpg");
        background-position: center;
        background-repeat: no-repeat;
        background-attachment: scroll;
        background-size: cover;

    }
    @media (min-width: 992px) {
        .masthead {
            height: calc(100vh - 56px);
            padding: 0;
        }
        .masthead h1 {
            line-height: 3.5rem;
        }
        .masthead h2 {
            max-width: 30rem;
            line-height: 2.5rem;

            font-size: 1.25rem;
        }
    }

</style>
