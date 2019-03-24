<template>
    <div
        id="demo"
        :class="[{'collapsed' : collapsed}]"
    >
        <div class="demo">
            <div class="container">
                <h1>
                    Co-operator
                </h1>
                <p>An ECSE 321 project</p>
                <hr style="margin: 50px 0px;border: 1px solid #e3e3e3;">
                <router-view />
        </div>
            <sidebar-menu
                :menu="menu"
                :collapsed="collapsed"
                :theme="selectedTheme"
                :show-one-child="true"
                @collapse="onCollapse"
                @itemClick="onItemClick"
            />
        </div>
    </div>
</template>

<script>
    const separator = {
        template: `<hr style="border-color: rgba(0, 0, 0, 0.1); margin: 20px;">`
    }
    export default {
        name:'app',

        created: function(){

        },
        data() {
            return {
                collapsed: false,
                themes: ['', 'white-theme'],
                selectedTheme: '',
                num_reminders : '3',
                menu: [
                    {
                        header: true,
                        title: 'Navigation'
                    },
                    {
                        href: '/hello',
                        title: 'Profile',
                        icon: 'fa fa-download'
                    },
                    {
                        href: '/list',
                        title: 'Internships',
                        icon: 'fa fa-code'
                    },
                    {
                        // href: '#',
                        title: 'Reminder',
                        icon: 'fa fa-cog',
                        //TODO get reminders to change the badge number
                        badge: {
                            text: 'new',
                            class: 'badge-danger'
                        }
                    },
                    {
                        title: 'Sign out',
                        icon: 'fa fa-code'
                    },
                    {
                        href: '/test',
                        title: 'test',
                        icon: 'fa fa-code'
                    }
                ]

            }
        },
        methods: {
            onCollapse (collapsed) {
                console.log(collapsed)
                this.collapsed = collapsed
            },
            onItemClick (event, item) {
                console.log('onItemClick')
                // console.log(event)
                // console.log(item)
            }
        }
    }
</script>

<style>
    @import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600');

    body,
    html {
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'Source Sans Pro', sans-serif;
        background-color: #f2f4f7;
    }

    #demo {
        padding-left: 350px;
    }
    #demo.collapsed {
        padding-left: 50px;
    }

    .demo {
        padding: 50px;
    }

    .badge-danger {
        background-color: #ff2a2a;
        color: #fff;
    }

    .container {
        max-width: 600px;
        margin-left: 10px;
    }

    pre {
        color: #2a2a2e;
        background: #fff;
        border-radius: 2px;
        padding: 10px;
        overflow: auto;
    }
</style>
