<template>
  <!-- 
    * 작성자 : 서울2반 4팀 윤지선
    * 내용 : 캡처를 위해 translate3d 위치 변경
    * 생성일자 : 2021-02-11
    * 최종수정일자 : 2021-02-18
  -->
  <div>
    <!-- 상단 Navi -->
    <back-navi
      v-if="where === 'update'"
      :title="title"
      v-on:checkbtn="checkHandler(no)"
      v-on:backbtn="backPage"
    ></back-navi>

    <div ref="mind-map-item" class="mind-map-item">
      <!-- <div id="saveBtn" v-if="button === true">
        <v-btn @click="updatemapdata">저장</v-btn>
      </div> -->
      <div class="tools updateTool" v-if="where === 'update'">
        <div
          v-tooltip.bottom="'화면 비율'"
          class="operation normal-icon"
          @click="toggleZoomSelector"
        >
          <span>{{ Math.round(ratio * 100) }}%</span>
          <i
            ref="ratio-selector"
            class="icon simple-vue-mind-map icon_arrow_down"
            style="margin-left: 3px;font-size: 6px;vertical-align: middle;"
          ></i>
          <ul ref="ratio-selector" class="ratio-selector">
            <li
              v-for="rt in selectableRatios"
              :key="rt"
              :class="{ active: rt === ratio }"
              @click="handleSelectZoomingRate(rt)"
            >
              {{ rt * 100 }}%
            </li>
          </ul>
        </div>

        <div v-tooltip.bottom="'확대'" class="operation" @click="handleZoomIn()">
          <i
            class="normal-icon icon simple-vue-mind-map icon_zoomin"
            :class="{ disabled: this.ratio >= this.MAX_RATIO }"
          ></i>
        </div>
        <div v-tooltip.bottom="'축소'" class="operation" @click="handleZoomOut()">
          <i
            class="normal-icon icon simple-vue-mind-map icon_zoomout"
            :class="{ disabled: this.ratio <= this.MIN_RATIO }"
          ></i>
        </div>
        <div class="separator"></div>
        <div v-tooltip.bottom="'가운데로 화면 이동'" class="operation" @click="handleRelocation">
          <i class="normal-icon icon simple-vue-mind-map icon_centerset"></i>
        </div>
        <div class="separator"></div>
        <div
          v-tooltip.bottom="'트리구조'"
          ref="toHori"
          class="operation"
          @click="handleToStructureHori"
          style="margin-right: 0;width: 40px;height: 24px;line-height: 24px;border: 1px solid #979797;border-radius: 4px 0 0 4px;"
        >
          <i class="normal-icon icon simple-vue-mind-map icon_structure_hori"></i>
        </div>
        <div
          v-tooltip.bottom="'트리구조'"
          ref="toVert"
          class="operation"
          @click="handleToStructureVert"
          style="margin-left: 0;width: 40px;height: 24px;line-height: 24px;border: 1px solid #979797;border-left: 0;border-radius: 0 4px 4px 0;"
        >
          <i class="normal-icon icon simple-vue-mind-map icon_structure_vert"></i>
        </div>
        <div v-if="showReason" class="separator"></div>
        <div
          v-if="showReason"
          v-tooltip.bottom="'부모노드 생성'"
          class="operation"
          @click="handleSetAsReason"
        >
          <i
            class="normal-icon"
            style="padding: 3px 6px;font-size: 12px;font-style: normal;border: 1px solid #979797;border-radius: 2px;"
            :class="{ active: currentData.reason > 0 }"
            >부모노드</i
          >
        </div>
        <div class="separator"></div>
        <div
          v-if="inFullScreen"
          v-tooltip.bottom="'전체화면 해제'"
          class="operation"
          @click="handleExitFullScreen"
        >
          <i class="normal-icon icon simple-vue-mind-map icon_widonws_mini"></i>
        </div>
        <div v-else v-tooltip.bottom="'전체화면'" class="operation" @click="handleFullScreen">
          <i class="normal-icon icon simple-vue-mind-map icon_widonws_max"></i>
        </div>
      </div>

      <div v-else class="tools">
        <div
          v-tooltip.bottom="'화면 비율'"
          class="operation normal-icon"
          @click="toggleZoomSelector"
        >
          <span>{{ Math.round(ratio * 100) }}%</span>
          <i
            ref="ratio-selector"
            class="icon simple-vue-mind-map icon_arrow_down"
            style="margin-left: 3px;font-size: 6px;vertical-align: middle;"
          ></i>
          <ul ref="ratio-selector" class="ratio-selector">
            <li
              v-for="rt in selectableRatios"
              :key="rt"
              :class="{ active: rt === ratio }"
              @click="handleSelectZoomingRate(rt)"
            >
              {{ rt * 100 }}%
            </li>
          </ul>
        </div>

        <div v-tooltip.bottom="'확대'" class="operation" @click="handleZoomIn()">
          <i
            class="normal-icon icon simple-vue-mind-map icon_zoomin"
            :class="{ disabled: this.ratio >= this.MAX_RATIO }"
          ></i>
        </div>
        <div v-tooltip.bottom="'축소'" class="operation" @click="handleZoomOut()">
          <i
            class="normal-icon icon simple-vue-mind-map icon_zoomout"
            :class="{ disabled: this.ratio <= this.MIN_RATIO }"
          ></i>
        </div>
        <div class="separator"></div>
        <div v-tooltip.bottom="'가운데로 화면 이동'" class="operation" @click="handleRelocation">
          <i class="normal-icon icon simple-vue-mind-map icon_centerset"></i>
        </div>
        <div class="separator"></div>
        <div
          v-tooltip.bottom="'트리구조'"
          ref="toHori"
          class="operation"
          @click="handleToStructureHori"
          style="margin-right: 0;width: 40px;height: 24px;line-height: 24px;border: 1px solid #979797;border-radius: 4px 0 0 4px;"
        >
          <i class="normal-icon icon simple-vue-mind-map icon_structure_hori"></i>
        </div>
        <div
          v-tooltip.bottom="'트리구조'"
          ref="toVert"
          class="operation"
          @click="handleToStructureVert"
          style="margin-left: 0;width: 40px;height: 24px;line-height: 24px;border: 1px solid #979797;border-left: 0;border-radius: 0 4px 4px 0;"
        >
          <i class="normal-icon icon simple-vue-mind-map icon_structure_vert"></i>
        </div>
        <div v-if="showReason" class="separator"></div>
        <div
          v-if="showReason"
          v-tooltip.bottom="'부모노드 생성'"
          class="operation"
          @click="handleSetAsReason"
        >
          <i
            class="normal-icon"
            style="padding: 3px 6px;font-size: 12px;font-style: normal;border: 1px solid #979797;border-radius: 2px;"
            :class="{ active: currentData.reason > 0 }"
            >부모노드</i
          >
        </div>
        <div class="separator"></div>
        <div
          v-if="inFullScreen"
          v-tooltip.bottom="'전체화면 해제'"
          class="operation"
          @click="handleExitFullScreen"
        >
          <i class="normal-icon icon simple-vue-mind-map icon_widonws_mini"></i>
        </div>
        <div v-else v-tooltip.bottom="'전체화면'" class="operation" @click="handleFullScreen">
          <i class="normal-icon icon simple-vue-mind-map icon_widonws_max"></i>
        </div>
      </div>

      <div ref="printMe" id="printMe">
        <div ref="drawing-board" class="drawing-board" id="mind">
          <div
            ref="tela"
            class="tela"
            @transitionstart="handleTransitionstart"
            @transitionend="handleTransitionend"
            @click="handleTelaClick"
          >
            <vue-okr-tree
              ref="tree"
              :direction="direction"
              :data="mapData"
              :render-content="handleRenderContent"
              @node-click="handleNodeClick"
            >
            </vue-okr-tree>
            <context-menu
              :show="contextMenuVisible"
              :offset="contextMenuOffset"
              @update:show="(show) => (contextMenuVisible = show)"
            >
              <a @click="handleSetAsReason">부모 노드</a>
              <a @click="handleAppendChild">노드추가</a>
              <a @click="handleRemove">노드 삭제</a>
            </context-menu>
          </div>
          <swipeable-bottom-sheet ref="swipeableBottomSheet">
            <v-col cols="12">
              <span>현재 노드 수정</span>
              <v-text-field
                class="mx-3"
                type="text"
                id="label"
                ref="label"
                v-model="currentData.label"
              >
                <template v-slot:append>
                  <v-icon @click="close()">mdi-check</v-icon>
                </template>
              </v-text-field>
              <v-btn @click="handleAppendChild">자식 노드 추가</v-btn>
            </v-col>
          </swipeable-bottom-sheet>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { SwipeableBottomSheet } from 'vue-swipeable-bottom-sheet';
import { VueOkrTree } from 'vue-okr-tree';
import 'vue-okr-tree/dist/vue-okr-tree.css';
import { ContextMenu } from '@gahing/vcontextmenu';
import '@gahing/vcontextmenu/lib/vcontextmenu.css';
import '../../assets/css/iconfont.css';
import Vue from 'vue';
import VTooltip from 'v-tooltip';
import BackNavi from '../navi/BackNavi.vue';

Vue.use(VTooltip);

export default {
  name: 'MindMap',
  props: ['data', 'width', 'height', 'showReason', 'dataTemplate', 'no', 'button', 'where'],
  components: {
    VueOkrTree,
    ContextMenu,
    SwipeableBottomSheet,
    BackNavi,
  },
  data() {
    return {
      title: '마인드맵 수정',
      sheet: false,
      tiles: [
        { img: 'keep.png', title: 'Keep' },
        { img: 'inbox.png', title: 'Inbox' },
        { img: 'hangouts.png', title: 'Hangouts' },
        { img: 'messenger.png', title: 'Messenger' },
        { img: 'google.png', title: 'Google+' },
      ],
      direction: 'horizontal',
      $toVert: null,
      $toHori: null,
      $mindMapItem: null,
      $ratioSelector: null,
      $drawingBoard: null,
      $tela: null,
      $mapTree: null,
      ratio: 1,
      selectableRatios: [0.25, 0.5, 0.75, 1, 1.25, 1.5],
      MIN_RATIO: 0.25,
      MAX_RATIO: 1.5,
      ratioSelectorShowing: false,
      inFullScreen: true,
      mapData: JSON.parse(JSON.stringify(this.data)),
      contextMenuVisible: false,
      contextMenuOffset: {
        left: 0,
        top: 0,
      },
      currentData: {},
      currentNode: null,
      currentComponent: null,
      inputContent: '',
      reasonCount: 0,
      output: null,
      message: '',
    };
  },
  mounted() {
    this.$refs.swipeableBottomSheet.setState('close');
    const $refs = this.$refs;

    this.$toVert = $refs.toVert;
    this.$toHori = $refs.toHori;
    this.$mindMapItem = $refs['mind-map-item'];
    this.$ratioSelector = $refs['ratio-selector'];
    this.$drawingBoard = $refs['drawing-board'];
    this.$tela = $refs.tela;
    this.$mapTree = document.querySelector('.org-chart-node');

    if (this.width) {
      this.$mindMapItem.style.width = `${this.width}px`;
    }

    if (this.height) {
      this.$mindMapItem.style.height = `${this.height}px`;
    }

    if (this.direction === 'vertical') {
      this.$mapTree.style.transform = `translate3d(0px, ${this.inFullScreen ? 75 : 35}px, 0)`;
    } else {
      this.$mapTree.style.transform = 'translate3d(0px, 0px, 0)';
    }
  },
  computed: {
    levelNodes() {
      const levelNodes = [];
      let currentLevel = this.mapData;

      while (currentLevel.length > 0) {
        let nextLevel = [];

        currentLevel.forEach((node) => {
          if (node.children && node.children.length > 0) {
            nextLevel = nextLevel.concat(node.children);
          }
        });

        levelNodes.push(currentLevel);

        currentLevel = nextLevel;
      }

      return levelNodes;
    },
  },
  methods: {
    open() {
      this.$refs.swipeableBottomSheet.setState('open');
    },
    close() {
      this.$refs.swipeableBottomSheet.setState('close');
    },
    toggleZoomSelector() {
      this.$ratioSelector.style.display = this.ratioSelectorShowing ? 'none' : 'block';

      this.ratioSelectorShowing = !this.ratioSelectorShowing;
    },
    handleSelectZoomingRate(ratio) {
      this.ratio = ratio;

      this.handleZoom();
    },
    handleZoomIn() {
      if (this.ratio >= this.MAX_RATIO) {
        return;
      }

      this.ratio += 0.1;

      this.handleZoom();
    },
    handleZoomOut() {
      if (this.ratio <= this.MIN_RATIO) {
        return;
      }

      this.ratio -= 0.1;

      this.handleZoom();
    },
    handleZoom() {
      this.$tela.style.transform = `scale(${this.ratio})`;

      this.handleRelocation();
    },
    handleTransitionstart() {
      // this.$tela.style.visibility = 'hidden'
    },
    handleTransitionend() {
      this.handleRelocation();

      this.$tela.style.visibility = 'visible';
    },
    handleRelocation() {
      const $drawingBoard = this.$drawingBoard;
      const $tela = this.$tela;
      const $mapTree = this.$mapTree;

      const drawingBoardRect = $drawingBoard.getBoundingClientRect();
      const telaRect = $tela.getBoundingClientRect();
      const mapTreeRect = $mapTree.getBoundingClientRect();

      if (this.direction === 'vertical') {
        $drawingBoard.scrollTo(
          mapTreeRect.x - telaRect.x - drawingBoardRect.width / 2 + mapTreeRect.width / 2,
          mapTreeRect.y - telaRect.y - 80
        );

        this.$mapTree.style.transform = `translate3d(0px, ${this.inFullScreen ? 75 : 35}px, 0)`;

        this.$toVert.classList.add('active');
      } else {
        $drawingBoard.scrollTo(0, mapTreeRect.y - telaRect.y - (this.inFullScreen ? 220 : 10));

        this.$mapTree.style.transform = 'translate3d(0px, 0px, 0)';

        this.$toHori.classList.add('active');
      }
    },
    handleToStructureVert() {
      this.$toHori.classList.remove('active');

      this.direction = 'vertical';

      this.handleRelocation();
    },
    handleToStructureHori() {
      this.$toVert.classList.remove('active');

      this.direction = 'horizontal';

      this.handleRelocation();
    },
    handleFullScreen() {
      this.$mindMapItem.classList.add('full-screen');

      this.$mindMapItem.style.width = '100%';
      this.$mindMapItem.style.height = '100%';

      this.inFullScreen = true;

      this.handleRelocation();
    },
    handleExitFullScreen() {
      this.$mindMapItem.classList.remove('full-screen');

      if (this.width) {
        this.$mindMapItem.style.width = `${this.width}px`;
      }

      if (this.height) {
        this.$mindMapItem.style.height = `${this.height}px`;
      }

      this.inFullScreen = false;

      this.handleRelocation();
    },
    // 노드 추가 부분
    handleRenderContent(h, node) {
      return (
        <div style="position: relative;text-align: left;">
          {node.data.root ? (
            ''
          ) : (
            <div class="act act-close" on-click={this.handleRemove}>
              <i class="fa fa-minus-square" aria-hidden="true"></i>
            </div>
          )}
          <span contenteditable="true" style="outline: none;">
            {node.data.label}
          </span>
          {this.showReason && node.data.reason ? (
            <div class="reason">부모노드{this.reasonCount > 1 ? node.data.reason : ''}</div>
          ) : (
            ''
          )}
        </div>
      );
    },
    handleNodeClick(data, node, component) {
      if (this.button === true) {
        this.open();
      }
      this.handleClearTela();

      this.inputContent = 'data.label';

      document.querySelectorAll('.org-chart-node-label-inner').forEach((item) => {
        item.style.backgroundColor = 'inherit';
      });

      const $ele = component.$el.querySelector('.org-chart-node-label-inner');

      $ele.focus();

      $ele.style.backgroundColor = '#FFF';

      $ele.querySelectorAll('.act').forEach((item) => {
        item.style.display = 'block';

        return item;
      });

      let isInputZh = false;
      // 입력 시작
      $ele.addEventListener(
        'compositionstart',
        function() {
          isInputZh = true;
        },
        false
      );
      // 입력 완료
      $ele.addEventListener(
        'compositionend',
        function() {
          isInputZh = false;

          var event = new Event('input', {
            bubbles: true,
            cancelable: true,
          });

          $ele.dispatchEvent(event);
        },
        false
      );

      $ele.addEventListener('input', (e) => {
        if (isInputZh) return;

        this.inputContent = e.target.innerText;
      });

      $ele.addEventListener('keydown', (e) => {
        if (isInputZh) return;

        if (e.key === 'Enter') {
          if (!e.shiftKey) {
            e.preventDefault();

            data.label = 'ddd';

            this.$emit('data-change', this.mapData);

            $ele.querySelector('span').blur();

            this.handleClearTela();
          }
        }
      });

      this.currentData = data;
      this.currentNode = node;
      this.currentComponent = component;
    },
    handleTelaClick(e) {
      let current = e.target;

      while (current !== this.$tela) {
        if (current.classList.contains('org-chart-node-label-inner')) {
          return;
        }

        current = current.parentNode;
      }

      this.handleClearTela();

      if (this.currentComponent) {
        this.currentData.label = this.currentComponent.$el.querySelector('span').innerText;

        this.$emit('data-change', this.mapData);
      }
    },
    handleClearTela() {
      document.querySelectorAll('.org-chart-node-label-inner').forEach((item) => {
        item.style.backgroundColor = 'inherit';

        item.querySelectorAll('.act').forEach((item) => {
          item.style.display = 'none';

          return item;
        });
      });
    },
    handleCoxMenu(e, data, node, component) {
      this.contextMenuVisible = true;
      this.contextMenuOffset = {
        left: e.pageX,
        top: e.pageY,
      };

      this.currentData = data;
      this.currentNode = node;
      this.currentComponent = component;
    },
    updateAllReason(currentReason) {
      this.levelNodes.forEach((level) => {
        level.forEach((node) => {
          if (node.reason >= currentReason) {
            node.reason--;
          }
        });
      });
    },
    handleSetAsReason() {
      if (this.currentData.reason > 0) {
        this.reasonCount--;

        this.updateAllReason(this.currentData.reason);

        this.currentData.reason = 0;
      } else {
        this.reasonCount++;

        this.currentData.reason = this.reasonCount;
      }

      this.contextMenuVisible = false;

      this.currentComponent.$el.click();
    },
    handleAppendChild() {
      this.$refs.tree.append(Object.assign({}, this.dataTemplate), this.currentNode);

      this.contextMenuVisible = false;

      this.$refs.swipeableBottomSheet.setState('close');
    },
    handleRemove() {
      this.$emit('node-delete', this.currentData, (val) => {
        if (val === true) {
          this.$refs.tree.remove(this.currentNode);

          this.contextMenuVisible = false;
        }
      });
    },

    // 부모 컴포넌트와의 통신을 위함
    // 바뀐 마인드맵 데이터 전달
    updatemapdata() {
      this.$emit('send', this.mapData);
    },
    // 수정 완료 버튼[YJS]
    checkHandler(no) {
      // 노드 수정 반영
      this.updatemapdata();

      // 썸네일 반영
      this.print(no);
    },
    // 뒤로 가기 버튼[YJS]
    backPage: function() {
      // 내 프로필 화면으로 이동
      this.$router.push('/main');
    },

    // 마인드맵 화면 캡처 로직
    async print(no) {
      let el = this.$refs.printMe;
      // el.appendChild(document.getElementById('mind'));

      let options = {
        type: 'dataURL',
      };
      this.output = await this.$html2canvas(el, options);

      var file = this.dataURLtoFile(this.output, 'capture.img');
      console.log(file);

      let form = new FormData();
      form.append('jwt', this.$store.getters.getJWT);
      form.append('MindID', no);
      form.append('files', file);

      this.$store.dispatch('captureImage', form).then(() => {
        // 응답 결과
        this.message = this.$store.getters.message;
        if (this.message === 'FAIL') console.log('실패');
        else console.log('성공');
      });
    },
    // 캡쳐한 이미지를 서버 전송을 위해 파일형식으로 변환 메소드
    dataURLtoFile(dataurl, fileName) {
      var arr = dataurl.split(','),
        mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]),
        n = bstr.length,
        u8arr = new Uint8Array(n);

      while (n--) {
        u8arr[n] = bstr.charCodeAt(n);
      }

      return new File([u8arr], fileName, { type: mime });
    },
  },
};
</script>

<style lang="scss">
.tooltip {
  display: block !important;
  z-index: 10000;
  font-size: 12px;

  .tooltip-inner {
    background: black;
    color: white;
    border-radius: 4px;
    padding: 5px 10px 4px;
  }

  .tooltip-arrow {
    width: 0;
    height: 0;
    border-style: solid;
    position: absolute;
    margin: 5px;
    border-color: black;
    z-index: 1;
  }

  &[x-placement^='top'] {
    margin-bottom: 5px;

    .tooltip-arrow {
      border-width: 5px 5px 0 5px;
      border-left-color: transparent !important;
      border-right-color: transparent !important;
      border-bottom-color: transparent !important;
      bottom: -5px;
      left: calc(50% - 5px);
      margin-top: 0;
      margin-bottom: 0;
    }
  }

  &[x-placement^='bottom'] {
    margin-top: 5px;

    .tooltip-arrow {
      border-width: 0 5px 5px 5px;
      border-left-color: transparent !important;
      border-right-color: transparent !important;
      border-top-color: transparent !important;
      top: -5px;
      left: calc(50% - 5px);
      margin-top: 0;
      margin-bottom: 0;
    }
  }

  &[x-placement^='right'] {
    margin-left: 5px;

    .tooltip-arrow {
      border-width: 5px 5px 5px 0;
      border-left-color: transparent !important;
      border-top-color: transparent !important;
      border-bottom-color: transparent !important;
      left: -5px;
      top: calc(50% - 5px);
      margin-left: 0;
      margin-right: 0;
    }
  }

  &[x-placement^='left'] {
    margin-right: 5px;

    .tooltip-arrow {
      border-width: 5px 0 5px 5px;
      border-top-color: transparent !important;
      border-right-color: transparent !important;
      border-bottom-color: transparent !important;
      right: -5px;
      top: calc(50% - 5px);
      margin-left: 0;
      margin-right: 0;
    }
  }

  &[aria-hidden='true'] {
    visibility: hidden;
    opacity: 0;
    transition: opacity 0.15s, visibility 0.15s;
  }

  &[aria-hidden='false'] {
    visibility: visible;
    opacity: 1;
    transition: opacity 0.15s;
  }
}
</style>

<style lang="scss" scoped>
$primaryColor: #3855ee;
$space: 24px;
$radius: 4px;

$disableColorPersent: 50%;

$normalIconNormalStatusColor: #aaaac0;
$normalIconHoverStatusColor: #565682;
$normalIconDisableStatusColor: rgba($normalIconNormalStatusColor, $disableColorPersent);

$primaryIconNormalStatusColor: #3855ee;
$primaryIconHoverStatusColor: #213ac3;
$primaryIconDisableStatusColor: rgba($primaryIconNormalStatusColor, $disableColorPersent);

$errorIconNormalStatusColor: #f56c6c;
$errorIconHoverStatusColor: #ff3333;
$errorIconDisableStatusColor: rgba($errorIconNormalStatusColor, $disableColorPersent);

.normal-icon {
  color: $normalIconNormalStatusColor;

  &:hover {
    color: $normalIconHoverStatusColor;
  }

  &.disabled {
    color: $normalIconDisableStatusColor;
  }
}

.primary-icon {
  color: $primaryIconNormalStatusColor;

  &:hover {
    color: $primaryIconHoverStatusColor;
  }

  &.disabled {
    color: $primaryIconDisableStatusColor;
  }
}

.error-icon {
  color: $errorIconNormalStatusColor;

  &:hover {
    color: $errorIconHoverStatusColor;
  }

  &.disabled {
    color: $errorIconDisableStatusColor;
  }
}

div {
  box-sizing: border-box;
}

.mind-map-item {
  position: relative;
  margin: 0 auto;
  width: 100%;
  height: 100%;
  margin-top: 43px;
}

.full-screen {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin-top: 100px;

  .tools {
    top: -40px;
  }
}

.tools {
  position: absolute;
  top: -6%;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  align-items: center;
  height: 40px;
  line-height: 40px;
  font-size: 12px;
  color: #4a4a4a;
  background-color: #fff;
  border: 1px solid #e5e9ed;
  border-radius: 4px;
  box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.4);
  user-select: none;
  z-index: 1;

  i {
    font-size: 18px;
  }

  .operation {
    position: relative;
    margin: 0 2px;
    padding: 0 8px;
    cursor: pointer;
    text-align: center;
  }

  .separator {
    margin: 0 10px;
    height: 24px;
    border: 1px solid #dcdfe6;
  }

  .active {
    border-color: $primaryColor;
    background-color: $primaryColor;
    color: #fff !important;

    i {
      color: #fff !important;
    }
  }

  .ratio-selector {
    display: none;
    position: absolute;
    top: 35px;
    left: 0;
    right: 0;
    padding: 0;
    text-align: center;
    background-color: #fff;

    li {
      padding: 0;
      list-style-type: none;
      border-top: 1px solid #eff6fb;

      &.active {
        color: #fff;
      }
    }
  }
}

.drawing-board {
  width: 100%;
  height: 600px;
  overflow: scroll;
  background-color: #f9f9f9;
}

.tela {
  width: 3000px;
  height: 3000px;
  background-color: #00000006;
  transition: transform 0s;
  transform-origin: 0 0;
  font-size: 14px;
  overflow: hidden;
}

::v-deep .focus {
  background-color: #fff;
}

::v-deep .act {
  display: none;
  position: absolute;
  color: $normalIconNormalStatusColor;
  z-index: 10000;

  &:hover {
    color: $normalIconHoverStatusColor;
  }

  &.disabled {
    color: $normalIconDisableStatusColor;
  }
}

::v-deep .act-left {
  top: 5px;
  left: -27px;
  transform: rotateZ(180deg);
}

::v-deep .act-right {
  top: 5px;
  right: -27px;
}

::v-deep .act-down {
  bottom: -28px;
  left: 50%;
  transform: translateX(-50%) rotateZ(90deg);
}

::v-deep .act-close {
  top: -26px;
  right: -11px;
}

::v-deep .reason {
  margin-top: 8px;
  box-sizing: border-box;
  width: 40px;
  height: 24px;
  line-height: 24px;
  color: #fff;
  background-color: $primaryColor;
  font-size: 12px;
  border-radius: $radius;
  text-align: center;
}

.context-menu {
  background-color: #fff;
  border: 1px solid #eee;
  box-shadow: 0 0.5em 1em 0 rgba(0, 0, 0, 0.1);
  border-radius: 1px;

  a {
    padding: 5px 10px;
    height: 28px;
    line-height: 28px;
    text-align: left;
    display: block;
    color: #1a1a1a;
    font-size: 16px;
    cursor: pointer;

    &:not(:last-child) {
      border-bottom: 1px solid #eee;
    }

    &:hover {
      background: $primaryColor;
      color: #fff;
    }
  }
}
#saveBtn {
  position: fixed;
  left: 5%;
  top: 10%;
  background-color: rgb(194, 7, 178);
}
#label {
  background-color: #fff;
}
#nodeBtn {
  margin: 10%;
}
::v-deep .org-chart-node-label {
  margin: 0 $space;
}

::v-deep .org-chart-node-label-inner {
  background-color: #fff;
  border-radius: 10px;
}
@media (min-width: 320px) and (max-width: 480px) {
}
</style>
