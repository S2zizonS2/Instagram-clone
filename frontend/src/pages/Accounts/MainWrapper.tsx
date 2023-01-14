import { SerializedStyles } from "@emotion/react";
import React from "react";
import Logo from "./Logo";
import WrapStyle from "./WrapStyle";

function MainWrapper(props: { children: React.ReactNode; style?: SerializedStyles }) {
    return (
        <main
            css={[WrapStyle, props?.style]}
        >
            <Logo />
            {props.children}
        </main>
    );
}

export default MainWrapper;