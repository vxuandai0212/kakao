<script lang="ts">
import { defineComponent } from 'vue';
import IconOptionPostStatus from './icons/IconOptionPostStatus.vue';
export default defineComponent({
    components: {
        IconOptionPostStatus
    },
    props: {
        avatar: { type: String, require: true },
        name: { type: String, require: true },
        timePost: { type: String, require: true },
        contentPost: { type: String, require: true }
    }
})
</script>

<template>
    <div style="display: flex; flex-direction: column; gap: 15px;">
        <div class="post-status">
            <div class="avatar-post-status">
                <img src="https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png"
                    alt="">
                <!-- <img :src="avatar" alt=""> -->
            </div>
            <div class="span-post-status">
                <span id="span-post-status1" style="margin-left: 5px;">Geralt of Rivia</span>
                <span id="span-post-status2" style="margin-left: 5px;">12 April at 09.28 PM</span>
            </div>

        </div>
        <div class="post-content">How's Your Day Going?</div>
    </div>
</template>

<style>
.post-content {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 24px;
    letter-spacing: 0.1px;
    color: #44444F;

}

.post-status {
    width: 533px;
    display: flex;
    gap: 10px;
    flex-direction: row;
}

.avatar-post-status img {
    width: 37.58px;
    height: 37.58px;
    border-radius: 50%;
    border: 1px solid #F1F1F5;
}

.span-post-status {
    display: flex;
    flex-direction: column;
    gap: 4px;
}

#icon-option-post-status {
    width: 44px;
    height: 93px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

#span-post-status1 {
    font-family: 'Poppins';
    font-style: normal;
    font-weight: 600;
    font-size: 14px;
    line-height: 21px;
    /* identical to box height */

    letter-spacing: 0.1px;

    color: #171725;
}

#span-post-status2 {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 12px;
    line-height: 14px;
    /* identical to box height */


    color: #92929D;
}

#span-post-status3 {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 24px;
    /* identical to box height, or 171% */

    letter-spacing: 0.1px;

    color: #44444F;
}
</style>