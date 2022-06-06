<template>
  <div class="home">
    <!-- <img alt="Vue logo" width="300" src="https://lh3.googleusercontent.com/blZEbd6tGdxd8MgEHQg5gNJsh5BUw5nhY5WxnCEIgq_MkHKpfJc09rDKhn2rpXTOlHCdU88kVigt2XSY9IQpJ5M0B2Jc8Ka1RA=s0" /> -->
    <h1>Carregar arquivos</h1>



  </div>

  <section class="body-about container-fluid">
      <img class="shadow" src="img/logo.png" width="150"
              height="100">
    <h2 class="p-4">Informações dos arquivos</h2>
    <div class="row">
      <div class="col">
        <div class="card green">
          <div class="card-body">
            <div ><img alt="Vue logo" width="25" src="img/cloud-arrow-up.svg" /> </div>
            <div ><h1>Documentos carregados: 1</h1></div>
          </div>
        </div>

      </div>
      <div class="col">
        <div class="card red">
          <div class="card-body">
            <div class="card-img"><img alt="Vue logo" width="25" src="img/trash.svg" /> </div>
            <h1>Documentos removidos: 1</h1>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card blue">
          <div class="card-body">
            <div class="card-img"><img alt="Vue logo" width="25" src="img/archive.svg" /> </div>
            <h1>Documentos processados: 1</h1>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <ul class="list-group">
          <li class="list-group-item">
            <div class="row">
              <div class="col p-4">
                <h3>Adicione o Relatório Anual(Form 20-F)</h3>
              </div>
              <div class="col">
                <div style="width: 500px;  position: relative;">
                  <div class="upload shadow  bg-white mouse">
                    <input type="file" name="upload" id="file" class="upload mouse" ref="file" multiple
                      v-on:change="onFilesSelected($event)" accept="image/*">
                    <p>
                      <i class="fa fa-cloud-upload mouse fa-2x"></i>
                      <span class="mouse"> Clique aqui</span>
                    </p>
                  </div>
                </div>
              </div>
              <div class="col">

              </div>
            </div>

          </li>
          <li class="list-group-item">
            <div class="row">
              <div class="col p-4">
                <h3>Adicione o Relatorio Integrado</h3>
              </div>
              <div class="col">
                <div style="width: 500px;  position: relative;">
                  <div class="upload shadow  bg-white mouse">
                    <input type="file" name="upload" id="file" class="upload mouse" ref="file" multiple
                      v-on:change="onFilesSelected($event)" accept="image/*">
                    <p>
                      <i class="fa fa-cloud-upload mouse fa-2x"></i>
                      <span class="mouse"> Clique aqui</span>
                    </p>
                  </div>
                </div>
              </div>
              <div class="col">

              </div>
            </div>

          </li>
          <li class="list-group-item">
            <div class="row">
              <div class="col p-4">
                <h3>Adicione o Relatório de Transparência Fiscal</h3>
              </div>
              <div class="col">
                <div style="width: 500px;  position: relative;">
                  <div class="upload shadow  bg-white mouse">
                    <input type="file" name="upload" id="file" class="upload mouse" ref="file" multiple
                      v-on:change="onFilesSelected($event)" accept="image/*">
                    <p>
                      <i class="fa fa-cloud-upload mouse fa-2x"></i>
                      <span class="mouse"> Clique aqui</span>
                    </p>
                  </div>
                </div>
              </div>
              <div class="col">

              </div>
            </div>

          </li>

        </ul>

      </div>
    </div>
    <div class="container mt-3 shadow p-4 mb-4 bg-white" v-for="(item, index) in documents" :key="index">

      <div class="row">
        <div class="col">
          <div class="thumbnail">
            <img class="shadow" v-bind:src="validImg(item.imagePreview) ? item.imagePreview : defaultImg" width="320"
              height="240">

          </div>
        </div>

        <div class="col mt-5 dados-arquivo">
          <p>Nome do Arquivo: {{ item.name }}</p>
          <p>Tipo do Arquivo: {{ item.type }}</p>
          <p>Tamanho do Arquivo: {{ item.size }}</p>
          <p>Data do Upload: {{ item.date }}</p>
        </div>

        <div class="col mt-5">
          <button type="button" v-on:click="processar()" class="btn btn-success mt-5">Processar</button>
          <button type="button" v-on:click="remove(index)" class="btn btn-danger mt-5 remover">Remover</button>
        </div>

      </div>
    </div>


  </section>

</template>

<script>
// @ is an alias to /src
// import MultipleFileUploader from '@updivision/vue2-multi-uploader'
// import vue2Dropzone from 'vue2-dropzone'
// import 'vue2-dropzone/dist/vue2Dropzone.min.css'
export default {
  name: 'Home',
  components: {
    // vue2Dropzone
    // MultipleFileUploader
  }, data: function () {
    return {
      dropzoneOptions: {
        url: 'https://httpbin.org/post',
        thumbnailWidth: 150,
        maxFilesize: 0.5,
        headers: { "My-Awesome-Header": "header value" }
      },
      defaultImg: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSEk2FrwLuQCnp7ijQSSUc7u6cWBFB0qyS5BZTGs35MGAvWoCeBMLi3d1U3acW-zUe6Nk&usqp=CAU",
      documents: []
    }
  }, methods: {
    remove($event){
        console.log($event);
        this.documents.pop($event);

    },
    processar(){
      this.$router.push('dashboard')
    },
    validImg(data) {
      return data.includes("data:image");
    },
    onFilesSelected($event) {
      let files = $event.target.files;
      for (var i in files) {
        let file = files[i];
        this.addDocument(file);
      }
    },
    addDocument(file) {
      let reader = new FileReader()
      var myBlob = new Blob([file], {
        type: file.type
      });
      reader.onload = function (event) {
        this.documents.push({
          'file': file,
          'imagePreview': event.target.result,
          'name': file.name,
          'type': file.type,
          'size': file.size,
          'date': new Date(),
        });
      }.bind(this);
      if (file.type == myBlob.type) {
        reader.readAsDataURL(myBlob);
      }

    }
  },
}
</script>

<style scoped>
.dados-arquivo {
  font-weight: bold;

}

.home {
  background-color: #2c3e50;
  color: white;
  padding: 10px 10px 10px 10px;
}

.body-about {
  /* 
  background-color: red; */
}

.remover{
  margin-left: 5px;
}

.red {
  background-color: rgb(189, 40, 26);
}

.blue {
  background-color: rgb(31 193 182);
}

.green{
  background-color: rgb(28, 147, 28);
}

.mouse {
  cursor: pointer;
  font-size: 1.5em;
}

#file {
  display: block !important;
  width: 100% !important;
  height: 45px !important;
  opacity: 0 !important;
  overflow: hidden !important;
  cursor: pointer;
}

.upload {
  border: 2px black dashed;
  margin-top: 15px;
  height: 50px !important;
}

.upload.input {
  width: 100%;
  height: 100%;
  background-color: red;
}

span {
  text-align: center;
}

div.upload {
  height: 10em;
  position: relative;
}

div.upload p {
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-right: -50%;
  transform: translate(-50%, -50%);
}
</style>
