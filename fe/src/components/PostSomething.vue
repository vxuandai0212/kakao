<script lang="ts">
import { defineComponent } from 'vue';
import IconClosePostSomething from './icons/IconClosePostSomething.vue';
import IconLoadingPostsomething from './icons/IconLoadingPostsomething.vue';
import IconImagePostSomething from './icons/IconImagePostSomething.vue';
import IconEmojiPostSomething from './icons/IconEmojiPostSomething.vue';
import IconPollingPostSomething from './icons/IconPollingPostSomething.vue';
import IconGIFPostSomething from './icons/IconGIFPostSomething.vue';
export default defineComponent({
    components: {
        IconClosePostSomething,
        IconLoadingPostsomething,
        IconImagePostSomething,
        IconEmojiPostSomething,
        IconPollingPostSomething,
        IconGIFPostSomething
    }
})
</script>

<template>
    <div class="post-something">
        <div class="span-post-st-icon-close">
            <span id="span-post-st">Post Something</span>
            <IconClosePostSomething id="icon-close-post-something" />
        </div>
        <div class="line-content-post-something">

        </div>
        <div class="content-post-something">
            <div class="img-text-icon-loading">
                <img src="https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png"
                    alt="">
                <input type="text" placeholder="What's on your mind?">
                <IconLoadingPostsomething />
            </div>
            <div class="line-content-post-something">

            </div>
            <div class="icons-img-emoji-gif-polling-button">
                <div class="icons-img-emoji-gif">
                    <IconImagePostSomething />
                    <IconEmojiPostSomething />
                    <IconGIFPostSomething />
                    <IconPollingPostSomething />
                </div>
                <div class="button-post-something">
                    <button>Share Post</button>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
.post-something {
    width: 625px;
    height: 181px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    margin: 0;
    padding: 0;
}

.span-post-st-icon-close {
    width: 625px;
    height: 41px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}

.content-post-something {
    display: flex;
    flex-direction: column;
}

.content-post-something img {
    width: 36px;
    height: 47.25px;
    border-radius: 50%;
    margin-left: 15px;
}

.img-text-icon-loading {
    display: flex;
    flex-direction: row;
    width: 625px;
    height: 71px;
    justify-content: space-around;
    align-items: center;
}

.img-text-icon-loading input {
    width: 500px;
    height: 25px;
    outline: none;
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 18px;
    border: none;
    background: none;
    outline: none;
}

.line-content-post-something {
    width: 595px;
    height: 1px;
    background: #F1F1F5;
}

.icons-img-emoji-gif-polling-button {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 595px;
    height: 68px;
    align-items: center;
    margin-left: 21.5px;
}

.icons-img-emoji-gif-polling-button .icons-img-emoji-gif {
    width: 137px;
    height: 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}

.icons-img-emoji-gif-polling-button .button-post-something button {
    width: 120px;
    height: 38px;
    background: #0062FF;
    border-radius: 10px;
    font-family: 'Poppins';
    font-style: normal;
    font-weight: 600;
    font-size: 12px;
    line-height: 18px;
    /* identical to box height */

    text-align: center;

    color: #FAFAFB;
    margin-left: 15px;
}

#span-post-st {
    margin-left: 20px;
    font-family: 'Poppins';
    font-style: normal;
    font-weight: 500;
    font-size: 14px;
    line-height: 21px;
    letter-spacing: 0.1px;

    color: #171725;
}

#icon-close-post-something {
    margin-right: 9px;
}
</style>