<template>
  <mindmap :nodes="nodes" :connections="connections" :editable="true" />
</template>

<script>
export default {
  name: 'MindMapUpdate',
  data() {
    return {
      nodes: [
        {
          'text': '중간발표',
          'url': '',
          'fx': -13.916222252976013,
          'fy': -659.1641376795345,
          'nodes': [],
        },
        {
          'text': '여행지',
          'url': '',
          'fx': 15.3247731601375,
          'fy': -964.73700786748157,
          'nodes': []
        },
        {
        'text': '음식',
        'url': '',
        'fx': 355.7839253819375,
        'fy': -455.5539283546699,
        'nodes': []
        },
        {
        'text': 'SNOW',
        'note': 'hihi',
        'url': '',
        'fx': -98.5231997717085,
        'fy': -355.07462866512333,
        'nodes': []
        },
      ],
      connections: [
        {
          'source': '중간발표',
          'target': 'SNOW',
          'curve': {
            'x': -43.5535,
            'y': 299.545
          }
        },
        {
          'source': '중간발표',
          'target': '여행지',
          'curve': {
            'x': -78.1206,
            'y': -114.714
          }
        },
        {
          'source': '중간발표',
          'target': '음식',
          'curve': {
            'x': 29.6649,
            'y': 80.1111
          }
        },
      ],
    }
  },
  methods: {
    
  }
}
</script>

<style>

</style>