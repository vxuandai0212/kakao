<script lang="ts">
import { defineComponent } from 'vue';
import MainComments from '@/components/icons/IconMainComments.vue'
export default defineComponent({
    components: {
        MainComments
    }
})
</script>

<template>
    <div class="main-comments">
        <div id="icon-main-comments">
            <MainComments />
        </div>
        <div id="span-main-comments">
            <span id="comments">Comments</span>
        </div>
    </div>
</template>

<style lang="scss">
.main-comments {
    width: 190px;
    height: 52px;
    border-radius: 15px;
    display: flex;
    align-items: center;
    cursor: pointer;
}

.main-comments #comments {
    font-family: 'Poppins';
    font-style: normal;
    font-weight: 500;
    font-size: 14px;
    line-height: 21px;
    /* identical to box height */
    letter-spacing: 0.1px;
    color: #0062FF;
}

#icon-main-comments {
    margin-left: 22px;
}

#span-main-comments {
    margin-left: 22px;
}
</style>