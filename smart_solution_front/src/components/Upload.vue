<template>
    <div class="upload shadow p-4 mb-4 bg-white mouse">
            <input
          type="file"
          name="upload"
          id="file"
          class="upload mouse"
          ref="file"
          multiple
          v-on:change="onFilesSelected($event)"
          accept="image/*"
        >
            <p>
                <i class="fa fa-cloud-upload mouse fa-2x"></i>
                <span class="mouse">Arraste arquivos</span>
            </p>
        </div>
</template>
<script>

import vue2Dropzone from "vue2-dropzone";
import "vue2-dropzone/dist/vue2Dropzone.min.css";
export default {
    components: {
        vueDropzone: vue2Dropzone
    }
}
</script>
<style lang="">
    .mouse {
    cursor: pointer;
    font-size: 1.5em;
}

#file {
    display: block !important;
    width: 100% !important;
    height: 145px !important;
    opacity: 0 !important;
    overflow: hidden !important;
    cursor: pointer;
}

.upload {
    border: 2px black dashed;
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