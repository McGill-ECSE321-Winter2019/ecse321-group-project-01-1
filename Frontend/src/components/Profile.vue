<template>
<div></div>
</template>

<script>
  export default {
    created: function() {
     this.onGuestRedirect();
    },
    props: {
      onGuestRedirect: { type: Function },
    },
    data() {
      return {
      }
    },
    methods: {
    }
  }
</script>