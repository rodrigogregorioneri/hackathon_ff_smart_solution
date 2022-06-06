<template>
  <div class="home">
    <!-- <img alt="Vue logo" width="300" src="https://lh3.googleusercontent.com/blZEbd6tGdxd8MgEHQg5gNJsh5BUw5nhY5WxnCEIgq_MkHKpfJc09rDKhn2rpXTOlHCdU88kVigt2XSY9IQpJ5M0B2Jc8Ka1RA=s0" /> -->
    <h1>Historico</h1>








  </div>
  <section class="body-about">

    <h2>Ultimos arquivos processados</h2>
    <div class="container mt-3 shadow p-4 mb-4 bg-white" v-for="(item, index) in documents" :key="index">

      <div class="row">
        <div class="col">
          <div class="thumbnail">
            <img class="shadow" width="320" height="240"  v-bind:src="defaultImg">

          </div>
        </div>

        <div class="col mt-5 dados-arquivo">
          <p>Nome do Arquivo: {{ item.name }}</p>
          <p>Tipo do Arquivo: {{ item.type }}</p>
          <p>Tamanho do Arquivo: {{ item.size }}</p>
          <p>Data do Upload: {{ item.date }}</p>
        </div>

        <div class="col mt-5">
          <button type="button" class="btn btn-warning mt-5 botoes">Detalhes</button>
          <button type="button" class="btn btn-success mt-5">Download</button>
        </div>

      </div>
    </div>


  </section>
</template>
<script>
export default {
  methods: {


  },

  data: function () {
    return {
      dropzoneOptions: {
        url: 'https://httpbin.org/post',
        thumbnailWidth: 150,
        maxFilesize: 0.5,
        headers: { "My-Awesome-Header": "header value" }
      },
      defaultImg: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSEk2FrwLuQCnp7ijQSSUc7u6cWBFB0qyS5BZTGs35MGAvWoCeBMLi3d1U3acW-zUe6Nk&usqp=CAU",
      documents: [{
        imagePreview: "img/pdf.jpeg",
        name: "pdf.jpeg",
        type: "image/jpeg",
        size: 4969,
        date: new Date()

      },
      {
        imagePreview: "img/pdf.jpeg",
        name: "pdf.jpeg",
        type: "image/jpeg",
        size: 4969,
        date: new Date()

      },
      {
        imagePreview: "img/pdf.jpeg",
        name: "pdf.jpeg",
        type: "image/jpeg",
        size: 4969,
        date: new Date()
      }]
    }
  }
}


</script>
<style scoped>
.botoes{
  margin-right: 12px;
}
.home {
  background-color: #2c3e50;
  color: white;
  padding: 10px 10px 10px 10px;
}

.body-about {
  margin: 10px 10px 10px 10px;
}
</style>
