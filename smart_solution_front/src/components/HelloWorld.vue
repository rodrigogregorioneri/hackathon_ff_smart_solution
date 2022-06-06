<template>
  <div class="hello">

    <section class="principal">
      <div>
        <div class="card-body">
                    <vue-dropzone ref="myVueDropzone" id="dropzone" :options="dropzoneOptions"></vue-dropzone>
        </div>
      </div>

    </section>


  </div>
</template>

<script>
import vue2Dropzone from 'vue2-dropzone'
import 'vue2-dropzone/dist/vue2Dropzone.min.css'
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  components: {
    vue2Dropzone
    // MultipleFileUploader
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* .principal{
  margin: 10px 10px 10px 10px;
} */

h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
