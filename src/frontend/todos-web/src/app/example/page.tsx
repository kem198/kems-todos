import { Page } from "@/components/layout/page";
import { TypographyH1 } from "@/components/typography/typography-h1";
import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function ExamplePage() {
  return (
    <Page>
      <TypographyH1>Example</TypographyH1>
      <ul>
        <li>
          <Link href="/example/greeting">
            <Button variant="link">/example/greeting</Button>
          </Link>
        </li>
        <li>
          <Link href="/example/hello">
            <Button variant="link">/example/hello</Button>
          </Link>
        </li>
        <li>
          <Link href="/example/shadcn">
            <Button variant="link">/example/shadcn</Button>
          </Link>
        </li>
      </ul>
    </Page>
  );
}
