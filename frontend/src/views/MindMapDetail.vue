<template>
	<div id="app">
		<mindmap
			:nodes="nodes"
			:connections="connections"
			:editable="true"
		/>
    <BottomNavi/>
	</div>
</template>

<script>
import map from "../map.js";
import BottomNavi from '@/components/navi/BottomNavi.vue'

export default {
  name: "app",
  components: {
    BottomNavi,
  },
  data() {
    return { ...map };
  }
};
</script>
