import React, { useContext, useMemo } from "react";
import { BsInstagram } from "react-icons/bs";
import BaseProps from "@/Type/BaseProps";
import DWordLogo from "@/assets/DLogo.svg";
import WWordLogo from "@/assets/WLogo.svg";
import { css } from "@emotion/react";
import ThemeContext from "@/store/ThemeContext";

/**
 * Aside Logo
 * @param props 
 * @returns 
 */
function Logo(props: BaseProps) {
    const { theme, themeStyle } = useContext(ThemeContext);
    const wordLogo = useMemo(() => theme === "light" ? DWordLogo : WWordLogo, [theme]);
    console.log(theme, themeStyle);
    return (
        <section
            css={style}
            className={props?.className}
        >
            <BsInstagram />
            <figure>
                <img src={wordLogo} />
            </figure>
        </section>
    );
}

const style = css`
    margin-bottom: 2rem;
    transition: all 2s;
    figure {
        width: 6.6rem;
        height: auto;
    }
    @media screen and (min-width: 1240px){
        svg {
            display: none;
        }
    }

    // 태블릿
    @media screen and (max-width: 1239px) and (min-width: 769px){
        figure {
            display: none;
        }
    }

    // 모바일
    @media screen and (max-width: 768px) {
        display: none;
    }

`;

export default Logo;