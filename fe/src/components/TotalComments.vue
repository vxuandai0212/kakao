<script lang="ts">
import { defineComponent } from 'vue';
import IconComment from '@/components/icons/IconComment.vue'
export default defineComponent({
    components: {
        IconComment
    },
    props: {
        totalComents: { type: Number, require: true }
    }
})
</script>

<template>
    <div class="total-comments">
        <div class="line-total-comments">

        </div>
        <div class="total-comments-icon-span">
            <IconComment />
            <span id="total-comments">{{ totalComents }} Comments</span>
        </div>
        <div class="line-total-comments">

        </div>
    </div>
</template>

<style>
.total-comments {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 24px;
    letter-spacing: 0.1px;
    color: #44444F;
}

.total-comments-icon-span {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}

#total-comments {
    margin-left: 14.5px;
}

.line-total-comments {
    width: 756.68px;
    height: 1px;
    background: #F1F1F5;
}
</style>