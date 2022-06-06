<script>
import SidebarLink from './SidebarLink'
import { collapsed, toggleSidebar, sidebarWidth } from './state'

export default {
  props: {},
  components: { SidebarLink },
  setup() {
    return { collapsed, toggleSidebar, sidebarWidth }
  }
}
</script>

<template>
  <div class="sidebar" :style="{ width: sidebarWidth }">
    <h1>
      <span v-if="collapsed">
        <div>F</div>
        <div>F</div>
      </span>
      <span v-else><img class="shadow" src="img/logo.png" width="140"
              height="100"></span>
    </h1>

    <SidebarLink to="/" icon="fas fa-archive">Carregar </SidebarLink>
    <SidebarLink to="/dashboard" icon="fas fa-columns">Gerar Arquivo</SidebarLink>
    <SidebarLink to="/about" icon="fas fa-list">Historico</SidebarLink>
    <!-- <SidebarLink to="/friends" icon="fas fa-users">Friends</SidebarLink>
    <SidebarLink to="/image" icon="fas fa-image">Images</SidebarLink> -->

    <span
      class="collapse-icon"
      :class="{ 'rotate-180': collapsed }"
      @click="toggleSidebar"
    >
      <i class="fas fa-angle-double-left" />
    </span>
  </div>
</template>

<style>
:root {
  --sidebar-bg-color: rgb(16, 126, 170);
  --sidebar-item-hover: rgb(16, 126, 170);
  --sidebar-item-active: rgb(16, 126, 170);
}
</style>

<style scoped>
.sidebar {
  color: white;
  background-color: var(--sidebar-bg-color);

  float: left;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  bottom: 0;
  padding: 0.5em;

  transition: 0.3s ease;

  display: flex;
  flex-direction: column;
}

.sidebar h1 {
  height: 2.5em;
}

.collapse-icon {
  position: absolute;
  bottom: 0;
  padding: 0.75em;

  color: rgba(255, 255, 255, 0.7);

  transition: 0.2s linear;
}

.rotate-180 {
  transform: rotate(180deg);
  transition: 0.2s linear;
}
</style>
